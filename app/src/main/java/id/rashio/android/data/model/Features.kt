package id.rashio.android.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import id.rashio.android.R

data class Features(
    @DrawableRes val imageFeature: Int,
    @StringRes val textFeature: Int,
    val destination: String
)

val listFeatures = listOf(
    Features(R.drawable.identify_skin_icon, R.string.identify_skin, "IdentifySkin"),
    Features(R.drawable.articles_icon, R.string.articles, "Articles"),
    Features(R.drawable.detection_history_icon, R.string.detection_history, "History"),
    Features(R.drawable.derma_icon, R.string.derma, "Derma")
)
