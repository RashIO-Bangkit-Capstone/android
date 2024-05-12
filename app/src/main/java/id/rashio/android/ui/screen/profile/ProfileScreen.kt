package id.rashio.android.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import id.rashio.android.R
import id.rashio.android.ui.components.BottomNavBar
import id.rashio.android.ui.components.BottomNavigationItem
import id.rashio.android.ui.components.TopBarComp
import id.rashio.android.ui.theme.poppinsFontFamily

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel(),
    navigateToBookmarkedArticles: () -> Unit,
    navigateToHistory: () -> Unit,
    navigateToAbout: () -> Unit,
    navigateToLogin: () -> Unit
) {

    val userData by viewModel.userData.collectAsState()

    Scaffold(topBar = {
        TopBarComp(
            title = "Profile",
            onBackClick = { navController.popBackStack() },
            actions = listOf {
                IconButton(onClick = {
                    viewModel.logout()
                    navigateToLogin()
                }) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_logout),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            })
    }, bottomBar = {
        BottomNavBar(
            navController = navController, items = BottomNavigationItem.getMenuBottomItems()
        )
    }, content = { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(200.dp)
                    .background(Color(0XFFF4DFBA))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_person),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.size(16.dp))
                Text(text = userData.name, color = Color.Black, fontWeight = FontWeight.Bold)
                Text(text = userData.email, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                Text(text = userData.phoneNumber, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            Spacer(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primary)
                    .fillMaxWidth()
                    .height(10.dp),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                        .clickable {
                            navigateToBookmarkedArticles()

                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        imageVector = Icons.Outlined.BookmarkBorder,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(40.dp)
                    )
                    Text(
                        text = "Bookmarked Articles",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = poppinsFontFamily,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }

            }
            Spacer(
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
                    .height(1.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .clickable {
                        navigateToHistory()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_article),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(36.dp)
                        .padding(start = 8.dp)
                )
                Text(
                    text = "Riwayat Deteksi",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier
                        .padding(start = 14.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
                    .height(1.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .clickable {
                        navigateToAbout()
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_question_mark_rounded),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .size(36.dp)
                        .padding(start = 8.dp)
                )
                Text(
                    text = "Tentang",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier
                        .padding(start = 14.dp)
                )
            }
            Spacer(
                modifier = Modifier
                    .background(Color.Gray)
                    .fillMaxWidth()
                    .height(1.dp),
            )
        }
    })
}
