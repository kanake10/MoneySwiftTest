package com.example.moneyswifttest.views


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.Modifier
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.domain.models.ProductsDomain
import com.example.moneyswifttest.viewmodels.ProductsViewModel

@Composable
fun ProductsScreen(
    navController: NavController,
    viewModel: ProductsViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.products) { data ->
                ProductsItem(
                    data = data,
                    onItemClick = {
                        navController.navigate(Screen.ProductDetailScreen.route + "/${data.id}")
                    }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        if(state.error.isNotBlank()) {
            Text(
                text = state.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if(state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
fun ProductsItem(data: ProductsDomain, onItemClick: (ProductsDomain) -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onPrimary
        ),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 3.dp
        )
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = rememberImagePainter(data = data.image),
                contentDescription = "Image",
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .padding(8.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .clickable { onItemClick(data) }
                    .weight(1f)
                    .padding(vertical = 8.dp)
                    .padding(start = 6.dp)
            ) {
                Text(
                    text = data.title,
                    textAlign = TextAlign.Start,
                    fontSize = 13.sp,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 5
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Price: $${data.price}",
                    fontSize = 12.sp,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.Light
                )
            }
        }
    }
}



sealed class Screen(val route: String) {
    object ProductsScreen: Screen("products")
    object ProductDetailScreen: Screen("product_details")
}