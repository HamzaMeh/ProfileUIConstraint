package com.arch.profileuiconstraint

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.SubcomposeAsyncImage
import com.arch.profileuiconstraint.ui.randomSizedPhotos
import com.arch.profileuiconstraint.ui.theme.DarkGreen
import com.arch.profileuiconstraint.ui.theme.Typography



@Preview(showBackground = true)
@Composable
fun ProfileUi() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (img) = createRefs()
        Image(
            modifier = Modifier
                .constrainAs(img) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }.fillMaxWidth(1f),
            painter = painterResource(id = R.drawable.rectangle_26), contentDescription =""
        )
    }

    Scaffold(
        backgroundColor = Color.Transparent,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Profile",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More",
                            tint = Color.White
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        },

        ) {
        val scrollState = rememberScrollState()
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(it)
                .verticalScroll(scrollState)
        ) {
            val (head, follow, friends, photos) = createRefs()
            Box(modifier = Modifier.constrainAs(head) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }) {
                Header(name = "Mark Wood", location = "London, UK")
            }
            Box(modifier = Modifier
                .constrainAs(follow) {
                    top.linkTo(head.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 20.dp)) {
                FollowersLike()
            }

            Box(modifier = Modifier
                .constrainAs(friends) {
                    top.linkTo(follow.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(top = 20.dp)) {
                Friends()
            }

            Box(
                modifier = Modifier
                    .constrainAs(photos) {
                        top.linkTo(friends.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(top = 20.dp),
            ) {
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    elevation = 4.dp,
                    shape = RoundedCornerShape(4.dp)
                ) {
                    ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                        val (txt, imgs) = createRefs()
                        Text(
                            text = "Photos",
                            style = Typography.h6,
                            modifier = Modifier
                                .padding(20.dp)
                                .constrainAs(txt) {
                                    top.linkTo(parent.top)
                                    start.linkTo(parent.start)
                                }
                        )
                        LazyVerticalStaggeredGrid(
                            columns = StaggeredGridCells.Adaptive(120.dp),
                            verticalItemSpacing = 4.dp,
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier
                                .constrainAs(imgs) {
                                    top.linkTo(txt.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    width = Dimension.fillToConstraints
                                }
                                .padding(horizontal = 20.dp)
                                .height(500.dp),
                            content = {
                                items(randomSizedPhotos.size) { index ->
                                    SubcomposeAsyncImage(
                                        model = randomSizedPhotos[index],
                                        loading = {
                                            CircularProgressIndicator(modifier = Modifier.size(30.dp))
                                        },
                                        contentDescription = "",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentHeight()
                                    )
                                }
                            },
                        )
                    }
                }
            }

        }

    }


}


@Composable
fun Header(name: String, location: String) {

    var text by remember { mutableStateOf("Follow") }
    var bgColor by remember {
        mutableStateOf(DarkGreen)
    }
    var textColor by remember {
        mutableStateOf(Color.White)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 40.dp, end = 10.dp)
            .background(Color.Transparent)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            shape = RoundedCornerShape(4.dp),
            elevation = 4.dp
        ) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
            ) {

                val (nameTv, icon, locationTv, btn) = createRefs()
                Text(
                    text = name,
                    style = Typography.h1,
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .constrainAs(nameTv) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.wrapContent
                        })

                Spacer(modifier = Modifier.padding(top = 4.dp))
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    modifier = Modifier
                        .alpha(0.5f)
                        .size(20.dp)
                        .constrainAs(icon) {
                            top.linkTo(nameTv.bottom)
                            start.linkTo(nameTv.start)
                        }
                )
                Text(
                    text = location,
                    style = Typography.body2,
                    modifier = Modifier
                        .padding(start = 2.dp)
                        .alpha(0.7f)
                        .constrainAs(locationTv) {
                            top.linkTo(nameTv.bottom)
                            start.linkTo(icon.end)
                        },
                )

                Button(
                    onClick = {
                        text = if (text == "Follow") {
                            bgColor = Color.White
                            textColor = Color.Black
                            "Following"
                        } else {
                            bgColor = DarkGreen
                            textColor = Color.White
                            "Follow"
                        }
                    },
                    modifier = Modifier
                        .padding(vertical = 20.dp)
                        .constrainAs(btn) {
                            top.linkTo(locationTv.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            width = Dimension.wrapContent
                        },
                    colors = ButtonDefaults.buttonColors(bgColor),
                    shape = RoundedCornerShape(30.dp),
                    border = if (bgColor == Color.White) BorderStroke(1.dp, DarkGreen) else null
                ) {
                    Text(
                        text = text,
                        style = Typography.h1,
                        color = textColor,
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 30.dp)
                    )
                }
            }
        }

        Image(
            painter = painterResource(id = R.drawable.image),
            contentDescription = "Profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(75.dp)
                .align(Alignment.TopCenter)
                .clip(CircleShape)
                .background(Color.Gray)
        )
    }
}

@Composable
fun FollowersLike() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(40.dp)
        ) {
            val (following, div1, follower, div2, like) = createRefs()
            Box(
                modifier = Modifier.constrainAs(following) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
            ) {
                CountDetails(count = "224k", type = "Following")
            }


            Divider(
                modifier = Modifier
                    .height(45.dp)
                    .width(1.dp)
                    .constrainAs(div1) {
                        top.linkTo(parent.top)
                        start.linkTo(following.end)
                    }
            )


            Box(
                modifier = Modifier.constrainAs(follower) {
                    top.linkTo(parent.top)
                    start.linkTo(div1.end)
                },
            ) {
                CountDetails(count = "48.6k", type = "Followers")
            }

            Divider(
                modifier = Modifier
                    .height(45.dp)
                    .width(1.dp)
                    .constrainAs(div2) {
                        top.linkTo(parent.top)
                        start.linkTo(follower.end)
                    }
            )

            Box(
                modifier = Modifier.constrainAs(like) {
                    top.linkTo(parent.top)
                    start.linkTo(div2.end)
                }
            ) {
                CountDetails(count = "3.2M", type = "Likes")
            }

            createHorizontalChain(
                following,
                div1,
                follower,
                div2,
                like,
                chainStyle = ChainStyle.SpreadInside
            )
        }


    }
}

@Composable
fun Friends() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            val (friendTv, seeTv) = createRefs()
            Text(
                text = "Friends",
                style = Typography.h6,
                modifier = Modifier.constrainAs(friendTv) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })
            Text(
                text = "See All",
                style = Typography.h6,
                color = Color.Blue,
                modifier = Modifier.constrainAs(seeTv) {
                    top.linkTo(parent.top)
                    start.linkTo(friendTv.end)
                })
            createHorizontalChain(friendTv, seeTv, chainStyle = ChainStyle.SpreadInside)

            val (friend1, friend2, friend3) = createRefs()

            BoxWithConstraints(modifier = Modifier
                .constrainAs(friend1) {
                    top.linkTo(friendTv.bottom)
                    start.linkTo(friendTv.start)
                }
                .padding(top = 20.dp)) {
                FriendBox(drawable = R.drawable.image, name = "Glenn")

            }

            BoxWithConstraints(modifier = Modifier
                .constrainAs(friend2) {
                    top.linkTo(friendTv.bottom)
                    start.linkTo(friend1.end)
                }
                .padding(top = 20.dp, start = 20.dp)) {
                FriendBox(drawable = R.drawable.image, name = "Mark")

            }

            BoxWithConstraints(modifier = Modifier
                .constrainAs(friend3) {
                    top.linkTo(friendTv.bottom)
                    start.linkTo(friend2.end)
                }
                .padding(top = 20.dp, start = 20.dp)) {
                FriendBox(drawable = R.drawable.image, name = "Hassan")

            }


        }
    }
}

@Composable
fun CountDetails(count: String, type: String) {
    ConstraintLayout() {
        val (heading, countTv) = createRefs()
        Text(text = count, style = Typography.h6, modifier = Modifier.constrainAs(heading) {
            top.linkTo(parent.top)
        }
        )
        Text(text = type, style = Typography.body1, modifier = Modifier.constrainAs(countTv) {
            top.linkTo(heading.bottom)
            start.linkTo(heading.start)
            end.linkTo(heading.end)
        })

    }
}


@Composable
fun FriendBox(drawable: Int, name: String) {
    ConstraintLayout(modifier = Modifier.width(50.dp)) {

        val (img, txt) = createRefs()
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "Image",
            modifier = Modifier
                .size(50.dp)
                .constrainAs(img) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .clip(CircleShape)
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )
        Text(name, style = Typography.body2, modifier = Modifier.constrainAs(txt) {
            top.linkTo(img.bottom)
            start.linkTo(img.start)
            end.linkTo(img.end)
        })

    }
}

@Composable
fun Photos() {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Photos",
                style = Typography.h6,
                modifier = Modifier.padding(vertical = 20.dp)
            )
            /*Image(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(id = R.drawable.image),
                contentDescription = ""
            )*/
        }

    }
}