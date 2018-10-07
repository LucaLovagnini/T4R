package com.example.llovagn.t4r

import android.media.Image
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.llovagn.t4r.model.CircularModel
import com.example.llovagn.t4r.model.Model
import com.example.llovagn.t4r.presenter.Presenter
import com.example.llovagn.t4r.presenter.PresenterImpl
import com.example.llovagn.t4r.view.ViewMVC
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import android.R.id.edit
import android.content.Context
import android.preference.PreferenceManager
import android.content.SharedPreferences
import com.google.gson.reflect.TypeToken
import android.content.Context.MODE_PRIVATE
import android.graphics.Color
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class MainActivity : AppCompatActivity(), ViewMVC {

    private lateinit var presenter: Presenter
    private var model : Model? = CircularModel(LinkedList<State>(Arrays.asList(
            State("First"),
            State("Second"),
            State("Third"),
            State("Fourth"),
            State("Fifth")

    )))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState != null ) {
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

        val context= applicationContext;

        val fos = context.openFileOutput("model.ser", Context.MODE_PRIVATE)
        val os = ObjectOutputStream(fos)
        os.writeObject(model)
        os.close()
        fos.close()
    }

    override fun onResume() {
        super.onResume()
        val context= applicationContext;
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
        mainTextView.text = message
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
