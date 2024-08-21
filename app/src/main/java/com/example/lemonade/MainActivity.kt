package com.example.lemonade

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeApp() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Lemonade",
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        }
    ) { innerPadding ->
        ImageAndText(
            Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        )
    }
}

@Composable
fun ImageAndText(modifier: Modifier = Modifier) {
    var step by remember {
        mutableStateOf(1)
    }
    var tap by remember {
        mutableStateOf(0)
    }
    var imageResource = when (step) {
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }
    var imageDescription = when (step) {
        1 -> stringResource(id = R.string.lemon_tree_image)
        2 -> stringResource(id = R.string.lemon_image)
        3 -> stringResource(id = R.string.lemonade_image)
        else -> stringResource(id = R.string.glass_image)
    }
    var stepMessage = when (step) {
        1 -> stringResource(id = R.string.tap_lemon_tree_message)
        2 -> stringResource(id = R.string.keep_tapping_lemon_message)
        3 -> stringResource(id = R.string.tap_lemonade_message)
        else -> stringResource(id = R.string.tap_glass_message)
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = {
            if (step == 1) {
                tap = (2..4).random()
                step++
            }
            else if (step == 2 && tap!=1)
                tap--
            else if (step == 4) {
                step = 1
                tap = (2..4).random()
            }
            else step++
        },
            colors = ButtonDefaults.buttonColors(
            containerColor = Color(0x8000CCCC),
            contentColor = Color.Black
        ),
            shape = RoundedCornerShape(48.dp)) {
            Image(
                painter = painterResource(id = imageResource),
                contentDescription = imageDescription
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = stepMessage, fontSize = 18.sp)
    }

}