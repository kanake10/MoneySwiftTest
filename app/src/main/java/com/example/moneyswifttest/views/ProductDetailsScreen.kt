package com.example.moneyswifttest.views


import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.draw.clip
import coil.compose.rememberImagePainter
import com.example.moneyswifttest.viewmodels.ProductViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.domain.models.ProductDomain
import com.example.moneyswifttest.R

@Composable
fun ProductDetailScreen(
    navController: NavController,
    id: Int,
    viewModel: ProductViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.getProductDetails(id)
    }

    val state = viewModel.state.value
    val isDarkTheme = isSystemInDarkTheme()

    Box(modifier = Modifier.fillMaxSize()) {
        state.productDetails?.let { details ->
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(20.dp),
            ) {
                item {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            painter = rememberImagePainter(data = details.image),
                            contentDescription = "Image",
                            modifier = Modifier
                                .size(220.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .padding(start = 50.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Details(
                            name = details.title,
                            description = details.description,
                            isDarkTheme=isDarkTheme
                        )
                        Spacer(modifier = Modifier.height(50.dp))
                        TotalAmountAndBuyButton(
                            totalAmount = details.price.toString(),
                            productDomain = details,
                            isDarkTheme = isDarkTheme
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Details(name: String, description: String,isDarkTheme: Boolean) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        Text(
            text = "Name",
            textAlign = TextAlign.Start,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 5,
            color = if (isDarkTheme) Color.White else Color.Black
        )
        Text(text = name,color = if (isDarkTheme) Color.White else Color.Black)
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        Text(
            text = "Description",
            textAlign = TextAlign.Start,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            maxLines = 5,
            color = if (isDarkTheme) Color.White else Color.Black
        )
        Text(text = description, style = MaterialTheme.typography.bodyMedium,color = if (isDarkTheme) Color.White else Color.Black)
        Divider(modifier = Modifier.padding(vertical = 8.dp))
    }
}


@Composable
fun TotalAmountAndBuyButton(
    totalAmount: String,
    productDomain: ProductDomain,
    isDarkTheme: Boolean
) {
    var showDialog by remember { mutableStateOf(false) }
    var showSuccessDialog by remember { mutableStateOf(false) }
    var showErrorSnackbar by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }
    var cardNumber by remember { mutableStateOf("") }
    var mmYear by remember { mutableStateOf("") }
    var cvc by remember { mutableStateOf("") }
    var country by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total Amount",
                modifier = Modifier.padding(start = 16.dp),
                color = if (isDarkTheme) Color.White else Color.Black
            )
            Text(
                text = "$$totalAmount",
                modifier = Modifier.padding(end = 16.dp),
                color = if (isDarkTheme) Color.White else Color.Black
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        Button(onClick = { showDialog = true }) {
            Text(
                text = "Buy Now",
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
                    .wrapContentSize(Alignment.Center)
            )
        }
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = {
                    Text("Payment Details")
                },
                text = {
                    Column {
                        OutlinedTextField(
                            value = cardNumber,
                            onValueChange = { newValue ->
                                cardNumber = newValue.take(16)
                                if (cardNumber.length == 16) {
                                    showErrorSnackbar = false
                                } else {
                                    showErrorSnackbar = true
                                    errorMessage = "Card number should be exactly 16 digits."
                                }
                            },
                            label = { Text("Card Number") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        OutlinedTextField(
                            value = mmYear.take(7),
                            onValueChange = { newValue ->
                                val strippedValue = newValue.replace("\\D".toRegex(), "")
                                val formatted = if (strippedValue.length <= 6) {
                                    val month = strippedValue.take(2).toIntOrNull() ?: 0
                                    val year = strippedValue.drop(2).take(4)
                                    val validatedMonth = when {
                                        month < 1 -> 1
                                        month > 12 -> 12
                                        else -> month
                                    }
                                    String.format("%02d/%s", validatedMonth, year)
                                } else {
                                    val month = mmYear.take(2)
                                    val year = mmYear.drop(3).take(4)
                                    String.format("%s/%s", month, year)
                                }
                                mmYear = formatted
                            },
                            label = { Text("MM/YY") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        OutlinedTextField(
                            value = cvc,
                            onValueChange = { newValue ->
                                cvc = newValue.take(3)
                                if (cvc.length == 3) {
                                    showErrorSnackbar = false
                                } else {
                                    showErrorSnackbar = true
                                    errorMessage = "CVC should be exactly 3 digits."
                                }
                            },
                            label = { Text("CVC") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        OutlinedTextField(
                            value = country,
                            onValueChange = { newValue ->
                                country = newValue
                            },
                            label = { Text("Country") }
                        )
                    }
                },
                confirmButton = {
                    Button(
                        onClick = {
                            if (cardNumber.isBlank() || mmYear.isBlank() || cvc.isBlank() || country.isBlank()) {
                                showErrorSnackbar = true
                                errorMessage = "Please fill in all required fields."
                            } else if (!showErrorSnackbar) {
                                showDialog = false
                                showSuccessDialog = true
                            }
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text("Pay ${productDomain.price}")
                        }
                    }
                }
            )

            if (showErrorSnackbar) {
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    contentColor = Color.White,
                    action = {
                        Text(
                            text = "Dismiss",
                            modifier = Modifier.clickable { showErrorSnackbar = false }
                        )
                    }
                ) {
                    Text(errorMessage)
                }
            }
        }

        if (showSuccessDialog) {
            AlertDialog(
                onDismissRequest = { showSuccessDialog = false },
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.payment_success),
                                contentDescription = "Success Image",
                                modifier = Modifier.size(150.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                "Your payment was successful.",
                                fontSize = 13.sp,
                            )
                        }
                    }
                },
                confirmButton = {
                }
            )
        }
    }
}













