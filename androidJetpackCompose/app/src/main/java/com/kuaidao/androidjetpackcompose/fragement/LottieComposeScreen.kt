package com.kuaidao.androidjetpackcompose.fragement

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.Lottie
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.*
import com.kuaidao.androidjetpackcompose.R

@Preview
@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition = composition,
        progress = { progress },
    )
}

@Composable
fun SampleLottie() {
    val composition1 by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val composition2 by rememberLottieComposition(LottieCompositionSpec.Url("https://..."))
// src/main/assets/animations/animation.json
    val composition3 by rememberLottieComposition(LottieCompositionSpec.Asset("animations/animation.json"))
}

@Composable
fun Loader2() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    LottieAnimation(composition)
}

@Composable
fun Lottie() {
    val compositionResult: LottieCompositionResult = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))

}