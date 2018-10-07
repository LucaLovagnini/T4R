package com.example.llovagn.t4r

import android.content.Context
import android.graphics.Color
import android.media.Image
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.widget.TextView
import com.example.llovagn.t4r.model.CircularModel
import com.example.llovagn.t4r.model.Model
import com.example.llovagn.t4r.presenter.Presenter
import com.example.llovagn.t4r.presenter.PresenterImpl
import com.example.llovagn.t4r.view.ViewMVC
import kotlinx.android.synthetic.main.activity_main.*
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.util.*


class MainActivity : AppCompatActivity(), ViewMVC {

    private lateinit var presenter: Presenter
    private var model: Model? = CircularModel(LinkedList<State>(Arrays.asList(
            State("First"),
            State("Second"),
            State("Third"),
            State("Fourth"),
            State("Fifth")

    )))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainTextView.setFactory {
            val textView = TextView(this@MainActivity)
            textView.textSize = 36f
            textView.gravity = Gravity.CENTER
            textView.setTextColor(Color.WHITE)
            textView.setShadowLayer(30f, 0f, 0f, Color.GRAY);
            textView
        }

        mainTextView.setInAnimation(this, android.R.anim.fade_in);
        mainTextView.setOutAnimation(this, android.R.anim.fade_out);


        if (savedInstanceState != null) {
            model = savedInstanceState.getParcelable("model")
        }
        presenter = PresenterImpl(this, model!!)
    }


    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelable("model", model!!)
        super.onSaveInstanceState(outState)
    }

    override fun onPause() {
        super.onPause()

        val context = applicationContext;

        val fos = context.openFileOutput("model.ser", Context.MODE_PRIVATE)
        val os = ObjectOutputStream(fos)
        os.writeObject(model)
        os.close()
        fos.close()
    }

    override fun onResume() {
        super.onResume()
        val context = applicationContext;
        val file = context.getFileStreamPath("model.ser")

        if (file == null || !file.exists()) {
            return
        }

        val fis = context.openFileInput("model.ser")
        val `is` = ObjectInputStream(fis)
        model = `is`.readObject() as Model
        presenter = PresenterImpl(this, model!!)
        `is`.close()
        fis.close()

    }

    override fun setBackgroundWithColor(androidColor: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setBackgroundWithImage(color: Image) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setMainTextView(message: String) {
        mainTextView.setText(message)
    }

    override fun playSong(state: State) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun onTouchBackground(view: View) {
        presenter.onClickBackground()
    }

    fun onTouchPreviousThing(view: View) {
        presenter.onClickPreviousButton()
    }
}
