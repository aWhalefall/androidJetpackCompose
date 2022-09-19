package com.kuaidao.androidjetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kuaidao.androidjetpackcompose.fragement.HomeScreen
import com.kuaidao.androidjetpackcompose.fragement.MineNavaHost
import com.kuaidao.androidjetpackcompose.fragement.ProfileScreen
import com.kuaidao.androidjetpackcompose.ui.theme.AndroidJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidJetpackComposeTheme {
                HomeScreen(modifier = Modifier)
                //                val navController = rememberNavController()
//                Column {
//                    Greeting(name = "hello world", onclilck = {
//                        this@MainActivity.startActivity(
//                            Intent(
//                                this@MainActivity, MainActivity2::class.java
//                            )
//                        )
//                    })
//
//                    Button(onClick = {
//                        val intent = Intent(Intent.ACTION_GET_CONTENT)
//                        intent.setType("*/*")
//                        intent.addCategory(Intent.CATEGORY_OPENABLE)
//                        startActivityForResult(Intent.createChooser(intent, "选择一个文件"), 1)
//                    }) {
//                        Text(text = "选择文件")
//                    }
//                    Spacer(modifier = Modifier.requiredHeight(30.dp))
//
//                    Button(onClick = { navController.navigate("profile") }) {
//                        Text(text = "导航")
//                    }
//                    MineNavaHost(navController = navController)
//                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, onclilck: () -> Unit, bgResource: ImageBitmap? = null) {
    Box(
        modifier = Modifier
            .wrapContentSize(Alignment.TopStart, false)
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .height(50.dp)
            .clip(RoundedCornerShape(12))
            .clickable(onClick = onclilck)
    ) {
        Image(
            painter = painterResource(id = R.mipmap.test), contentDescription = null
        )
    }


//    Button(onClick = onclilck, modifier = Modifier.paint(BitmapPainter(image = bgResource))) {
//
//    }

//    Box(
//        modifier = Modifier
//            .wrapContentWidth()
//            .wrapContentHeight()
//    ) {
//
//        Image(painter = painterResource(id = R.mipmap.ic_launcher), contentDescription = null)
//    }


//    Column {
//
//
//        Column {
//            Text(text = "Hello $name!")
//            Text(text = "Hello $name!")
//            Text(text = "Hello $name!")
//        }
//        Spacer(modifier = Modifier.requiredHeight(50.dp))
//        Row {
//            Text(text = "12121 $name!")
//            Text(text = "Hello $name!")
//        }
//    }


}

@Composable
fun ButtonBackGround() {
    Surface(
        modifier = Modifier
            .width(250.dp)
            .height(45.dp), shape = RoundedCornerShape(22.5.dp)
    ) {
        Button(
            onClick = { /*TODO*/ }, modifier = Modifier
                .paint(
                    painter = BitmapPainter(
                        image = ImageBitmap.imageResource(
                            id = R.mipmap.test
                        )
                    )
                )
                .width(250.dp)
                .height(45.dp)
        ) {
            Text(text = "hello")
        }
    }

}


@Preview(showBackground = true, showSystemUi = false)
@Composable
fun DefaultPreview() {
    AndroidJetpackComposeTheme {
        Greeting("Android", {}, ImageBitmap.imageResource(id = R.mipmap.test))
    }
}

@Preview(showSystemUi = true, device = "spec:parent=pixel_5,orientation=landscape")
@Composable
fun HomeScrrenSample() {
    AndroidJetpackComposeTheme {
        HomeScreen(modifier = Modifier)
    }
}

@Composable
fun BtnPreview() {
    Column {
        Box(
            Modifier
                .requiredHeight(50.dp)
                .wrapContentWidth()
                .background(MaterialTheme.colorScheme.onSurface),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .padding(start = 20.dp, end = 20.dp),
                painter = painterResource(id = R.drawable.span_primary_small_bg),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
            Text(text = "第一个文本控件ikkokokojih呼呼呼呼呼呼")
        }
        Spacer(modifier = Modifier.height(10.dp))
        Column(modifier = Modifier.requiredHeight(50.dp)) {
            Image(
                modifier = Modifier.fillMaxSize(1f),
                painter = painterResource(id = R.mipmap.test),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(Modifier.requiredHeight(50.dp)) {
            Image(
                modifier = Modifier.fillMaxSize(1f),
                painter = painterResource(id = R.mipmap.test),
                contentDescription = null,
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Composable
fun SurfaceShow() {
    Surface(
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(0.5.dp, Color.Green),  // 边框
        modifier = Modifier
            .padding(10.dp)
            .requiredHeight(50.dp),  // 外边距
//        color = Color.Black,  // 背景色
        contentColor = Color.Blue,
    ) {
        Image(
            painter = painterResource(id = R.mipmap.test),
            contentDescription = null,
            contentScale = ContentScale.FillBounds
        )
        Box(
            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter
        ) {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "按钮Button")
            }
        }
    }
}