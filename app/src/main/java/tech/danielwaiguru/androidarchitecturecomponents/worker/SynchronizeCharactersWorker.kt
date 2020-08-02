package tech.danielwaiguru.androidarchitecturecomponents.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.HttpException
import tech.danielwaiguru.androidarchitecturecomponents.repositories.CharacterRepository
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
            Result.success()
        } catch (e: HttpException){
            Log.d("WORKER", e.message())
            Result.retry()
        }
    }
}