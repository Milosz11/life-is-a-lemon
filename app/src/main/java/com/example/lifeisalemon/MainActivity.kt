package com.example.lifeisalemon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lifeisalemon.ui.theme.LifeIsALemonTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LifeIsALemonTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    LemonadeButtons(
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun LemonadeButtons(modifier: Modifier = Modifier) {
    var state by remember { mutableIntStateOf(0) }
    var imageId = when (state) {
        0 -> R.drawable.lemon_tree
        1 -> R.drawable.lemon_squeeze
        2 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    var contentDescriptionId = when (state) {
        0 -> R.string.cont_desc_lemon_tree
        1 -> R.string.cont_desc_lemon_squeeze
        2 -> R.string.cont_desc_lemon_drink
        else -> R.string.cont_desc_lemon_restart
    }
    var textId = when (state) {
        0 -> R.string.text_desc_lemon_tree
        1 -> R.string.text_desc_lemon_squeeze
        2 -> R.string.text_desc_lemon_drink
        else -> R.string.text_desc_lemon_restart
    }
    var tapsRemaining = if (state == 1) (2..4).random() else 0

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = Modifier.padding(16.dp),
            onClick = {
                if (state == 1) {
                    tapsRemaining--
                    if (tapsRemaining <= 0) state++
                } else {
                    state++
                    state %= 4
                }
            }
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = stringResource(id = contentDescriptionId)
            )
        }

        Text(
            text = stringResource(id = textId),
            modifier = Modifier.padding(16.dp),
            fontSize = 18.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LifeIsALemonTheme {
        LemonadeApp()
    }
}
