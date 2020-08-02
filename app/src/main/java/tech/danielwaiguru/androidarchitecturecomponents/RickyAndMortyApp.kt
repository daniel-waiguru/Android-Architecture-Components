package tech.danielwaiguru.androidarchitecturecomponents

import android.app.Application
import androidx.work.*
import dagger.hilt.android.HiltAndroidApp
import tech.danielwaiguru.androidarchitecturecomponents.worker.SynchronizeCharactersWorker
import tech.danielwaiguru.androidarchitecturecomponents.worker.SynchronizeCharactersWorker.Companion.SYNCHRONIZE_WORKER
import java.util.concurrent.TimeUnit

@HiltAndroidApp
class RickyAndMortyApp: Application(){
    override fun onCreate() {
        super.onCreate()
        dataSync()
    }
    private fun dataSync(){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .build()
        val synchronizeCharactersWorker =
            PeriodicWorkRequestBuilder<SynchronizeCharactersWorker>(20, TimeUnit.MINUTES)
                .setConstraints(constraints)
                .build()
        val workManager = WorkManager.getInstance(this)
        workManager.enqueueUniquePeriodicWork(
            SYNCHRONIZE_WORKER,
            ExistingPeriodicWorkPolicy.KEEP,
            synchronizeCharactersWorker
        )
    }
}
