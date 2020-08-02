package tech.danielwaiguru.androidarchitecturecomponents.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import retrofit2.HttpException
import tech.danielwaiguru.androidarchitecturecomponents.R
import tech.danielwaiguru.androidarchitecturecomponents.repositories.CharacterRepository
import tech.danielwaiguru.androidarchitecturecomponents.utils.sendNotification
import javax.inject.Inject
class SynchronizeCharactersWorker(context: Context, workerParameters: WorkerParameters):
CoroutineWorker(context, workerParameters){
    @Inject
    lateinit var characterRepository: CharacterRepository
    companion object{
        const val SYNCHRONIZE_WORKER = "characters_worker"
    }
    override suspend fun doWork(): Result {
        return try {
            characterRepository.fetchCharacters()
            createNotifications()
            Result.success()
        } catch (e: HttpException){
            Log.d("WORKER", e.message())
            Result.retry()
        }
    }
    private fun createNotifications(){
        val notificationManger = ContextCompat.getSystemService(applicationContext,
            NotificationManager::class.java) as NotificationManager
        notificationManger.sendNotification(
            "Synchronizing characters data",
            applicationContext)
        createChannel(applicationContext.getString(R.string.channel_id),
            applicationContext.getString(R.string.channel_name))
    }
    private fun createChannel(channelId: String, channelName: String){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager = applicationContext
                    .getSystemService(NotificationManager::class.java) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}