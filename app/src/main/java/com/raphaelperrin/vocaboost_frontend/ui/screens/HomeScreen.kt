import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.raphaelperrin.vocaboost_frontend.ui.components.HomeHeaderWidget
import com.raphaelperrin.vocaboost_frontend.ui.components.HomePracticeWidget
import com.raphaelperrin.vocaboost_frontend.ui.components.HomeProgressWidget
import com.raphaelperrin.vocaboost_frontend.ui.components.HomeQuickPracticeWidget
import com.raphaelperrin.vocaboost_frontend.ui.components.HomeStatsWidget
import com.raphaelperrin.vocaboost_frontend.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val user by homeViewModel.currentUser.collectAsState()
    user?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp)
                .height(IntrinsicSize.Min),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            HomeHeaderWidget(user = it)
            HomeProgressWidget()
            Row(
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                HomePracticeWidget(
                    modifier = Modifier.weight(1f)
                )
                HomeStatsWidget(
                    modifier = Modifier.weight(1f)
                )
            }
            HomeQuickPracticeWidget()
        }
    }
}