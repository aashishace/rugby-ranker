package dev.ricknout.rugbyranker.live.work

import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dev.ricknout.rugbyranker.core.model.Sport

class LiveMatchWorkManager(private val workManager: WorkManager) {
    fun enqueueWork(
        sport: Sport,
        matchId: String,
    ) {
        val data =
            Data.Builder()
                .putString(KEY_MATCH_ID, matchId)
                .build()
        val builder =
            when (sport) {
                Sport.MENS -> OneTimeWorkRequestBuilder<MensLiveMatchWorker>()
                Sport.WOMENS -> OneTimeWorkRequestBuilder<WomensLiveMatchWorker>()
            }
        val work =
            builder
                .setInputData(data)
                .build()
        val uniqueWorkName = getUniqueWorkName(sport, matchId)
        workManager.enqueueUniqueWork(uniqueWorkName, EXISTING_WORK_POLICY, work)
    }

    private fun getUniqueWorkName(
        sport: Sport,
        matchId: String,
    ) = when (sport) {
        Sport.MENS -> MensLiveMatchWorker.getUniqueWorkName(matchId)
        Sport.WOMENS -> WomensLiveMatchWorker.getUniqueWorkName(matchId)
    }

    companion object {
        const val KEY_MATCH_ID = "match_id"
        private val EXISTING_WORK_POLICY = ExistingWorkPolicy.REPLACE
    }
}
