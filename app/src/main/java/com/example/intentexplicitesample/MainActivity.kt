package com.example.intentexplicitesample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    companion object {
        val EXTRA_MESSAGE = "com.example.android.firstActivity.extra.MESSAGE"
        val TEXT_REQUEST = 1
    }
    private val LOG_TAG = MainActivity::class.java.simpleName
    lateinit var mMessageEditText:EditText
    lateinit var mReplyHeadTextView: TextView
    lateinit var mReplyTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mMessageEditText = findViewById(R.id.editText_main)
        mReplyHeadTextView = findViewById(R.id.text_header_reply);
        mReplyTextView = findViewById(R.id.text_message_reply);
    }
    fun launchSecondActivity(view: View?) {
        Log.d(LOG_TAG, "Button clicked!")
        val message = mMessageEditText.text.toString()

        var intent = Intent(this, secondactivity::class.java)
        intent.putExtra(EXTRA_MESSAGE, message);
        resultLauncher.launch(intent)
        //startActivityForResult(intent, TEXT_REQUEST);
        //startActivity(intent)
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val reply = result.data?.getStringExtra(secondactivity.EXTRA_REPLY)
            mReplyHeadTextView.setVisibility(View.VISIBLE)
            mReplyTextView.setText(reply)
            mReplyTextView.setVisibility(View.VISIBLE)
        }
    }

}