package com.example.breakfastapp

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.breakfastapp.ui.theme.BreakfastAppTheme
import androidx.compose.runtime.*
import androidx.compose.ui.draw.rotate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BreakfastAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BreakfastScreen()
                }
            }
        }
    }
}

@Composable
fun BreakfastScreen() {
    // Replace this with your data fetching logic
    val breakfastTips = sampleBreakfastData()

    LazyColumn {
        items(breakfastTips) { tip ->
            BreakfastTipCard(tip = tip)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun BreakfastTipCard(tip: BreakfastTip) {
    var isDescriptionVisible by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Day ${tip.id}",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = tip.title,
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.height(8.dp))


            Image(
                painter = painterResource(id = tip.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            IconToggleButton(
                checked = isDescriptionVisible,
                onCheckedChange = { isDescriptionVisible = it },
                modifier = Modifier
                    .rotate(if (isDescriptionVisible) 180f else 0f)
                    .align(Alignment.CenterHorizontally)
            ) {
                if (isDescriptionVisible) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = null
                    )
                } else {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
            }
            AnimatedVisibility(
                visible = isDescriptionVisible,
                enter = fadeIn(
                    initialAlpha = 0.3f,
                    animationSpec = tween(durationMillis = 300)
                ),
                exit = fadeOut(
                    animationSpec = tween(durationMillis = 300)
                )
            ) {
                Text(
                    text = tip.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

data class BreakfastTip(
    val id: Int,
    val day: String,
    val title: String,
    val description: String,
    val imageRes: Int // This should be a drawable resource ID
)

fun sampleBreakfastData(): List<BreakfastTip> {
    return listOf(
        BreakfastTip(
            id = 1,
            day = "Day 1",
            title = "Greek Yogurt Parfait",
            description = "Layer Greek yogurt with fresh berries and granola for a delicious and nutritious breakfast.",
            imageRes = R.drawable.breakfast1
        ),
        BreakfastTip(
            id = 2,
            day = "Day 2",
            title = "Oatmeal with Almonds and Honey",
            description = "Cook up a bowl of oatmeal and top it with sliced almonds and a drizzle of honey for a satisfying meal.",
            imageRes = R.drawable.breakfast2
        ),
        BreakfastTip(
            id = 3,
            day = "Day 3",
            title = "Scrambled Eggs with Spinach",
            description = "Whisk together eggs and sauté with spinach for a protein-packed breakfast that's also high in vitamins.",
            imageRes = R.drawable.breakfast3
        ),
        BreakfastTip(
            id = 4,
            day = "Day 4",
            title = "Smoothie Bowl",
            description = "Blend your favorite fruits and Greek yogurt into a thick smoothie, then top with nuts and seeds.",
            imageRes = R.drawable.breakfast4
        ),
        BreakfastTip(
            id = 5,
            day = "Day 5",
            title = "Whole Wheat Pancakes",
            description = "Make pancakes using whole wheat flour and serve with a side of fresh fruit and a drizzle of maple syrup.",
            imageRes = R.drawable.breakfast5
        ),
        BreakfastTip(
            id = 6,
            day = "Day 6",
            title = "Avocado Toast",
            description = "Spread mashed avocado on whole-grain toast and add a pinch of salt and pepper for a quick and healthy breakfast.",
            imageRes = R.drawable.breakfast6
        ),
        BreakfastTip(
            id = 7,
            day = "Day 7",
            title = "Chia Seed Pudding",
            description = "Mix chia seeds with almond milk and let it sit overnight. Top with fresh fruit and nuts in the morning.",
            imageRes = R.drawable.breakfast7
        ),
        BreakfastTip(
            id = 8,
            day = "Day 8",
            title = "Fruit Salad",
            description = "Toss together a variety of fresh fruits for a colorful and refreshing breakfast salad.",
            imageRes = R.drawable.breakfast8
        ),
        BreakfastTip(
            id = 9,
            day = "Day 9",
            title = "Peanut Butter Banana Toast",
            description = "Spread peanut butter on whole-grain toast and add banana slices for a tasty and filling breakfast.",
            imageRes = R.drawable.breakfast9
        ),
        BreakfastTip(
            id = 10,
            day = "Day 10",
            title = "Yogurt and Berry Parfait",
            description = "Layer yogurt with mixed berries and a sprinkle of granola for a simple and nutritious breakfast.",
            imageRes = R.drawable.breakfast10
        ),
        BreakfastTip(
            id = 11,
            day = "Day 11",
            title = "Vegetable Omelette",
            description = "Whisk eggs and sauté with diced vegetables of your choice for a hearty and veggie-packed breakfast.",
            imageRes = R.drawable.breakfast11
        ),
        BreakfastTip(
            id = 12,
            day = "Day 12",
            title = "Cottage Cheese with Pineapple",
            description = "Top cottage cheese with fresh pineapple chunks for a protein-rich and tropical breakfast.",
            imageRes = R.drawable.breakfast12
        ),
        BreakfastTip(
            id = 13,
            day = "Day 13",
            title = "Whole Grain Cereal",
            description = "Choose a whole grain cereal and enjoy it with low-fat milk or a dairy-free alternative.",
            imageRes = R.drawable.breakfast13
        ),
        BreakfastTip(
            id = 14,
            day = "Day 14",
            title = "Egg and Vegetable Wrap",
            description = "Scramble eggs and add sautéed vegetables, then wrap it all in a whole wheat tortilla for a portable breakfast.",
            imageRes = R.drawable.breakfast14
        ),
        BreakfastTip(
            id = 15,
            day = "Day 15",
            title = "Peach and Almond Smoothie",
            description = "Blend peaches, almond milk, and a scoop of protein powder for a peachy and protein-rich breakfast.",
            imageRes = R.drawable.breakfast15
        ),
        BreakfastTip(
            id = 16,
            day = "Day 16",
            title = "Whole Grain Waffles",
            description = "Make waffles using whole grain flour and top them with fresh berries and a dollop of yogurt.",
            imageRes = R.drawable.breakfast16
        ),
        BreakfastTip(
            id = 17,
            day = "Day 17",
            title = "Spinach and Feta Omelette",
            description = "Whisk eggs and fill your omelette with spinach and crumbled feta cheese for a flavorful breakfast.",
            imageRes = R.drawable.breakfast17
        ),
        BreakfastTip(
            id = 18,
            day = "Day 18",
            title = "Cherry Almond Oatmeal",
            description = "Add dried cherries and chopped almonds to your oatmeal for a delightful and nutty twist.",
            imageRes = R.drawable.breakfast18
        ),
        BreakfastTip(
            id = 19,
            day = "Day 19",
            title = "Banana Walnut Muffins",
            description = "Bake banana walnut muffins using whole wheat flour for a grab-and-go breakfast option.",
            imageRes = R.drawable.breakfast19
        ),
        BreakfastTip(
            id = 20,
            day = "Day 20",
            title = "Sliced Apple with Almond Butter",
            description = "Spread almond butter on apple slices for a simple and crunchy breakfast.",
            imageRes = R.drawable.breakfast20
        ),
        BreakfastTip(
            id = 21,
            day = "Day 21",
            title = "Quinoa Breakfast Bowl",
            description = "Cook quinoa and top it with sliced banana, chopped nuts, and a drizzle of honey for a hearty breakfast bowl.",
            imageRes = R.drawable.breakfast21
        ),
        BreakfastTip(
            id = 22,
            day = "Day 22",
            title = "Mixed Berry Smoothie",
            description = "Blend mixed berries, Greek yogurt, and a touch of honey for a vibrant and creamy smoothie.",
            imageRes = R.drawable.breakfast22
        ),
        BreakfastTip(
            id = 23,
            day = "Day 23",
            title = "Peanut Butter and Jelly Sandwich",
            description = "Spread natural peanut butter and low-sugar jelly on whole-grain bread for a nostalgic breakfast.",
            imageRes = R.drawable.breakfast23
        ),
        BreakfastTip(
            id = 24,
            day = "Day 24",
            title = "Egg White Scramble",
            description = "Whisk egg whites and cook with diced vegetables for a low-fat and protein-rich breakfast.",
            imageRes = R.drawable.breakfast24
        ),
        BreakfastTip(
            id = 25,
            day = "Day 25",
            title = "Mango and Spinach Smoothie",
            description = "Blend mango, spinach, and coconut water for a tropical and nutritious breakfast drink.",
            imageRes = R.drawable.breakfast25
        ),
        BreakfastTip(
            id = 26,
            day = "Day 26",
            title = "Whole Grain Cinnamon Toast",
            description = "Toast whole-grain bread and sprinkle with cinnamon and a touch of honey for a simple yet flavorful breakfast.",
            imageRes = R.drawable.breakfast26
        ),
        BreakfastTip(
            id = 27,
            day = "Day 27",
            title = "Fruit and Nut Oatmeal",
            description = "Add your favorite fruits and a handful of nuts to your morning oatmeal for added flavor and texture.",
            imageRes = R.drawable.breakfast27
        ),
        BreakfastTip(
            id = 28,
            day = "Day 28",
            title = "Cottage Cheese and Berries",
            description = "Pair cottage cheese with a variety of fresh berries for a protein-packed and fruity breakfast.",
            imageRes = R.drawable.breakfast28
        ),
        BreakfastTip(
            id = 29,
            day = "Day 29",
            title = "Veggie Breakfast Burrito",
            description = "Scramble eggs with diced vegetables and wrap them in a whole wheat tortilla for a satisfying breakfast burrito.",
            imageRes = R.drawable.breakfast29
        ),
        BreakfastTip(
            id = 30,
            day = "Day 30",
            title = "Avocado Toast with Poached Egg",
            description = "Spread mashed avocado on whole-grain toast and top it with a poached egg for a protein-packed breakfast.",
            imageRes = R.drawable.breakfast30
        )
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
        BreakfastScreen()
}