package com.kuaidao.androidjetpackcompose.layout

import android.view.Gravity
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.kuaidao.androidjetpackcompose.R
import kotlin.math.max


@Composable
fun ColumVertical() {
    BodyContent()
}

val topics = listOf(
    "Arts & Crafts", "Beauty", "Books", "Business", "Comics", "Culinary",
    "Design", "Fashion", "Film", "History", "Maths", "Music", "People", "Philosophy",
    "Religion", "Social sciences", "Technology", "TV", "Writing"
)

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Row(modifier = modifier
        .background(color = Color.LightGray)
        .padding(16.dp),
        content = {
            ComposeFlowLayout(
                itemSpacing = 16.dp,
                lineSpacing = 16.dp,
                gravity = Gravity.TOP
            ) {
                for (index in topics.indices) {
                    if (index % 3 == 1) {
                        Chip(modifier = Modifier.height(80.dp), text = topics[index])
                    } else {
                        Chip(text = topics[index])
                    }
                }
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chip(modifier: Modifier = Modifier, text: String) {
    Card(
        modifier = modifier,
        border = BorderStroke(color = Color.Black, width = Dp.Hairline),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp, 16.dp)
                    .background(color = MaterialTheme.colors.secondary)
            )
            Spacer(Modifier.width(4.dp))
            Text(text = text)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun FlowLayout() {
    Row(modifier = Modifier
        .padding(16.dp),
        content = {
            ComposeFlowLayout(
                itemSpacing = 5.dp,
                lineSpacing = 10.dp,
                gravity = Gravity.TOP,
                content = {
                    topics.forEach {
                        Row(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .clip( shape = RoundedCornerShape(corner = CornerSize(5.dp)))
                                .background(
                                    color = colorResource(id = R.color.f2f5ff),
                                )
                                .padding(horizontal = 5.dp, vertical = 2.dp)
                                .clickable(onClick = {}),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = it)
                            Spacer(modifier = Modifier.requiredWidth(5.dp))
                            Icon(
                                painter = painterResource(id = R.mipmap.search_huo_icon),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                        }
                    }
                })
        })

}

private fun getItemTop(gravity: Int, lineHeight: Int, topOffset: Int, childHeight: Int): Int {
    return when (gravity) {
        Gravity.CENTER -> topOffset + (lineHeight - childHeight) / 2
        Gravity.BOTTOM -> topOffset + lineHeight - childHeight
        else -> topOffset
    }
}


@Composable
fun ComposeFlowLayout(
    modifier: Modifier = Modifier,
    itemSpacing: Dp = 0.dp,
    lineSpacing: Dp = 0.dp,
    gravity: Int = Gravity.TOP,
    content: @Composable () -> Unit
) {
    Layout(modifier = modifier, content = content, measurePolicy = { measurables, constraints ->
        val parentWidthSize = constraints.maxWidth
        var layoutHeight = 0
        var rowHeight = 0
        var rowWidth = 0

        val mAllPlaceables = mutableListOf<MutableList<Placeable>>()  // 所有子项
        val mLineHeight = mutableListOf<Int>() //每行的最高高度
        var lineViews = mutableListOf<Placeable>() //每行放置的内容

        measurables.mapIndexed { index, measurable ->
            // 测量子view
            val placeablle = measurable.measure(constraints)
            val childWidth = placeablle.width
            val childHeight = placeablle.height
            //如果行宽超出Layout宽度则换行
            if (childWidth + rowWidth > parentWidthSize) {

                //每行最大高度添加到列表中
                mLineHeight.add(rowHeight)
                //二级列表，存放所有子项
                mAllPlaceables.add(lineViews)
                //重置每行子项列表
                lineViews = mutableListOf()
                lineViews.add(placeablle)

                layoutHeight += childHeight
                rowHeight = childHeight
                rowWidth = childWidth
                layoutHeight += lineSpacing.toPx().toInt()


            } else {
                //记录每行宽度,列宽
                rowWidth += childWidth + if (index == 0) 0 else itemSpacing.toPx().toInt()
                //记录每行最大高度
                rowHeight = maxOf(rowHeight, childHeight)

                //每行的子项添加到列表中
                lineViews.add(placeablle)
            }
            //最后一行特殊处理
            if (index == measurables.size - 1) {
                layoutHeight += rowHeight

                mLineHeight.add(rowHeight)
                mAllPlaceables.add(lineViews)
            }
        }
        layout(parentWidthSize, layoutHeight) {
            var topOffset = 0
            var leftOffset = 0
            for (index in mAllPlaceables.indices) {
                lineViews = mAllPlaceables[index]
                rowHeight = mLineHeight[index]
                for (j in lineViews.indices) {
                    val child = lineViews[j]
                    val childWidth = child.width
                    val childHeight = child.height
                    // 根据Gravity获取子项y坐标
                    val childTop = getItemTop(gravity, rowHeight, topOffset, childHeight)
                    child.placeRelative(leftOffset, childTop)
                    // 更新子项x坐标
                    leftOffset += childWidth + itemSpacing.toPx().toInt()
                }
                //重置子项y坐标
                leftOffset = 0
                topOffset += lineSpacing.toPx().toInt() + rowHeight
            }
        }

    })

}


@Composable
fun VerticalColum(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    Layout(modifier = modifier,
        content = content,
        measurePolicy = MeasurePolicy { measurables, constraints ->
            val placeables = measurables.map { measurable ->
                //测量子项
                measurable.measure(constraints = constraints)
            }

            layout(constraints.maxWidth, constraints.maxHeight) {
                var yPosition = 0
                var xPosition = 0
                placeables.forEach { placeable ->
                    //在屏幕上定位子项
                    placeable.placeRelative(x = xPosition, y = yPosition)
                    //记录子项目y轴位置
                    //yPosition += placeable.height
                    xPosition += placeable.width
                }
            }
        })
}