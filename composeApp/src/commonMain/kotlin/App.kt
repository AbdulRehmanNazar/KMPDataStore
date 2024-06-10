import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.local.DataStoreRepository
import data.local.createDataStore
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.random.Random

@Composable
@Preview
fun App(context: Any? = null) {

    MaterialTheme {
        val corotuineScope = rememberCoroutineScope()
        val dataStoreRepo = remember { DataStoreRepository(dataStore = createDataStore(context)) }
        var timeStamp: Long? by remember { mutableStateOf(null) }


        LaunchedEffect(Unit) {
            dataStoreRepo.readTimeStamp().collectLatest { savedTimeStamp ->
                timeStamp = savedTimeStamp
            }
        }

        Column(
            modifier = Modifier.fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            Text(text = "Saved value: ${if (timeStamp == null) "Empty" else "$timeStamp"}")
            Spacer(modifier = Modifier.padding(12.dp))
            Button(onClick = {
                corotuineScope.launch {
                    dataStoreRepo.saveTimeStamp(Random.nextLong(100, 1000))
                }
            }) {
                Text(text = "Save Random Number")
            }
        }
    }
}