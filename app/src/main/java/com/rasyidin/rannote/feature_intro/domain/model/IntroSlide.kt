package com.rasyidin.rannote.feature_intro.domain.model

import com.rasyidin.rannote.R

data class IntroSlide(
    val title: String,
    val desc: String,
    val imageSlide: Int
) {
    companion object {
        val DATA_SLIDE =
            listOf(
                IntroSlide(
                    "Welcome!",
                    "",
                    R.color.colorBlackVariant
                ),
                IntroSlide(
                    "Let's take Notes!",
                    "Write down everything you think is important and interesting to note!",
                    R.drawable.ic_note
                ),
                IntroSlide(
                    "Reach Your Target!",
                    "Write down what you want to achieve by making a to-do list!",
                    R.drawable.img_todo_list
                ),
                IntroSlide(
                    "Manage Your Finance!",
                    "You can have financial records that are neatly arranged according to date!",
                    R.drawable.img_finance
                ),
            )

    }
}
