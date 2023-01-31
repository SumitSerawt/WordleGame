package com.example.wordle

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

//object FourLetterWordList {
    // List of most common 4 letter words from: https://7esl.com/4-letter-words/
    val fourLetterWords =
        "Area,Army,Baby,Back,Ball,Band,Bank,Base,Bill,Body,Book,Call,Card,Care,Case,Cash,City,Club,Cost,Date,Deal,Door,Duty,East,Edge,Face,Fact,Farm,Fear,File,Film,Fire,Firm,Fish,Food,Foot,Form,Fund,Game,Girl,Goal,Gold,Hair,Half,Hall,Hand,Head,Help,Hill,Home,Hope,Hour,Idea,Jack,John,Kind,King,Lack,Lady,Land,Life,Line,List,Look,Lord,Loss,Love,Mark,Mary,Mind,Miss,Move,Name,Need,News,Note,Page,Pain,Pair,Park,Part,Past,Path,Paul,Plan,Play,Post,Race,Rain,Rate,Rest,Rise,Risk,Road,Rock,Role,Room,Rule,Sale,Seat,Shop,Show,Side,Sign,Site,Size,Skin,Sort,Star,Step,Task,Team,Term,Test,Text,Time,Tour,Town,Tree,Turn,Type,Unit,User,View,Wall,Week,West,Wife,Will,Wind,Wine,Wood,Word,Work,Year,Bear,Beat,Blow,Burn,Call,Care,Cast,Come,Cook,Cope,Cost,Dare,Deal,Deny,Draw,Drop,Earn,Face,Fail,Fall,Fear,Feel,Fill,Find,Form,Gain,Give,Grow,Hang,Hate,Have,Head,Hear,Help,Hide,Hold,Hope,Hurt,Join,Jump,Keep,Kill,Know,Land,Last,Lead,Lend,Lift,Like,Link,Live,Look,Lose,Love,Make,Mark,Meet,Mind,Miss,Move,Must,Name,Need,Note,Open,Pass,Pick,Plan,Play,Pray,Pull,Push,Read,Rely,Rest,Ride,Ring,Rise,Risk,Roll,Rule,Save,Seek,Seem,Sell,Send,Shed,Show,Shut,Sign,Sing,Slip,Sort,Stay,Step,Stop,Suit,Take,Talk,Tell,Tend,Test,Turn,Vary,View,Vote,Wait,Wake,Walk,Want,Warn,Wash,Wear,Will,Wish,Work,Able,Back,Bare,Bass,Blue,Bold,Busy,Calm,Cold,Cool,Damp,Dark,Dead,Deaf,Dear,Deep,Dual,Dull,Dumb,Easy,Evil,Fair,Fast,Fine,Firm,Flat,Fond,Foul,Free,Full,Glad,Good,Grey,Grim,Half,Hard,Head,High,Holy,Huge,Just,Keen,Kind,Last,Late,Lazy,Like,Live,Lone,Long,Loud,Main,Male,Mass,Mean,Mere,Mild,Nazi,Near,Neat,Next,Nice,Okay,Only,Open,Oral,Pale,Past,Pink,Poor,Pure,Rare,Real,Rear,Rich,Rude,Safe,Same,Sick,Slim,Slow,Soft,Sole,Sore,Sure,Tall,Then,Thin,Tidy,Tiny,Tory,Ugly,Vain,Vast,Very,Vice,Warm,Wary,Weak,Wide,Wild,Wise,Zero,Ably,Afar,Anew,Away,Back,Dead,Deep,Down,Duly,Easy,Else,Even,Ever,Fair,Fast,Flat,Full,Good,Half,Hard,Here,High,Home,Idly,Just,Late,Like,Live,Long,Loud,Much,Near,Nice,Okay,Once,Only,Over,Part,Past,Real,Slow,Solo,Soon,Sure,That,Then,This,Thus,Very,When,Wide"

    // Returns a list of four letter words as a list
    fun getAllFourLetterWords(): List<String> {
        return fourLetterWords.split(",")
    }

    // Returns a random four letter word from the list in all caps
    fun getRandomFourLetterWord(): String {
        val allWords = getAllFourLetterWords()
        val randomNumber = (0..allWords.size).shuffled().last()
        return allWords[randomNumber].uppercase()
    }
//}

class MainActivity : AppCompatActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        var number = 0
        val buttonR = findViewById<Button>(R.id.button2)
        buttonR.visibility = View.INVISIBLE



        var textView1 = findViewById<EditText>(R.id.editTextTextPersonName15)
        var textView2 = findViewById<EditText>(R.id.editTextTextPersonName14)
        var textView3 = findViewById<EditText>(R.id.editTextTextPersonName13)
        var textView4 = findViewById<EditText>(R.id.editTextTextPersonName10)
        var textView5 = findViewById<EditText>(R.id.editTextTextPersonName11)
        var textView6 = findViewById<EditText>(R.id.editTextTextPersonName12)



        button.setOnClickListener{

            hideMyKeyBoard()

            var text = findViewById<EditText>(R.id.et_simple)
            var value = text.text.toString()
            value = value.uppercase()



                number++
                when (number) {
                    1 -> {
                        textView1.setText(value)
                        textView2.setText(checkGuess(value))
                        if (value == wordToGuess){
                            Toast.makeText(applicationContext, "Word Found!", Toast.LENGTH_SHORT).show()
                            button.isEnabled = false
                            button.isClickable = false
                            buttonR.visibility = View.VISIBLE
                        }

                    }
                    2 -> {
                        textView3.setText(value)
                        textView4.setText(checkGuess(value))
                        if (value == wordToGuess){
                            Toast.makeText(applicationContext, "Word Found!", Toast.LENGTH_SHORT).show()
                            button.isEnabled = false
                            button.isClickable = false
                            buttonR.visibility = View.VISIBLE
                        }
                    }
                    3 -> {
                        textView5.setText(value)
                        textView6.setText(checkGuess(value))
                        if (value == wordToGuess){
                            Toast.makeText(applicationContext, "Word Found!", Toast.LENGTH_SHORT).show()
                            button.isEnabled = false
                            button.isClickable = false
                            buttonR.visibility = View.VISIBLE
                        }
                        else{
                            Toast.makeText(applicationContext, "Right word:$wordToGuess", Toast.LENGTH_SHORT).show()
                            button.isEnabled = false
                            button.isClickable = false
                            buttonR.visibility = View.VISIBLE
                        }
                    }

                }


            }

        buttonR.setOnClickListener{

            number = 0
            button.isEnabled = true
            button.isClickable = true
            buttonR.visibility = View.INVISIBLE
            textView1.setText("")
            textView2.setText("")
            textView3.setText("")
            textView4.setText("")
            textView5.setText("")
            textView6.setText("")


        }

    }

    private fun hideMyKeyBoard(){
        val view = this.currentFocus
        if (view != null){
            val hideMe = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideMe.hideSoftInputFromWindow(view.windowToken, 0)
        }
        else{
            window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        }
    }



    private val wordToGuess = getRandomFourLetterWord()

    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }



}










