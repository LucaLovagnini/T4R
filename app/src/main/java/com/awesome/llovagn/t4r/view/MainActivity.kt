package com.awesome.llovagn.t4r.view

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.awesome.llovagn.t4r.R
import com.awesome.llovagn.t4r.R.drawable.*
import com.awesome.llovagn.t4r.R.raw.*
import com.awesome.llovagn.t4r.model.CircularModel
import com.awesome.llovagn.t4r.model.Model
import com.awesome.llovagn.t4r.presenter.Presenter
import com.awesome.llovagn.t4r.presenter.PresenterImpl
import com.awesome.llovagn.t4r.repository.RepositoryStateImplCircularModel
import com.awesome.llovagn.t4r.state.State
import com.awesome.llovagn.t4r.state.StateImpl
import com.awesome.llovagn.t4r.util.MusicHandler
import kotlinx.android.synthetic.main.activity_main.*
import pl.droidsonroids.gif.GifImageView
import java.util.*


class MainActivity : AppCompatActivity(), ViewInter {

    private lateinit var presenter: Presenter
    private var playingSong: MusicHandler? = null
    private val fadeDuration = 2000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val states: Deque<State> = LinkedList<State>(Arrays.asList(
                StateImpl("Life is Strange", chloeandmaxnew, R.raw.maxandchloe),
                StateImpl("Aida", 0, R.raw.aida),
                StateImpl("Fantasia", fantasia, R.raw.fantasiasong),
                StateImpl("Cartoni Morti", cartonimorti, 0),
                StateImpl("Il Piccolo Principe", piccoloprincipe, 0, Color.YELLOW),
                StateImpl("Surf Camp", tent, R.raw.rain),
                StateImpl("Casa Patas", 0, flamenco, Color.RED),
                StateImpl("Partner", partner),
                StateImpl("Cloud Atlas", cloudatlas, R.raw.endtitle),
                StateImpl("Tre Colli", 0, 0, Color.GREEN),
                StateImpl("The Wolf", 0, R.raw.thewolf, Color.DKGRAY),
                StateImpl("Queen\nLive Aid", wembley, R.raw.wembleysound),
                StateImpl("Memola", 0, R.raw.forest, Color.GREEN),
                StateImpl("Totoro", totoro, R.raw.totorosong),
                StateImpl("Silence", 0, 0, Color.BLACK),
                StateImpl("Road to Bilbao", 0, R.raw.newslang, Color.argb(255, 0, 212, 232)),
                StateImpl("Volterra", volterra),
                StateImpl("#girodeilGiappone", girodeilgiappone),
                StateImpl("Vivere la Vita", 0, R.raw.viverelavita),
                StateImpl("Bojack", bojack),
                StateImpl("Grotta Byron", wavesandseagull),
                StateImpl("Spirited Away",spiritedaway),
                StateImpl("Happy!", happy),
                StateImpl("Peñalara", penalara),
                StateImpl("Bimbi Sperduti",bimbisperduti),
                StateImpl("La Stanza al Quarto Piano in Calle de la Cabeza"),
                StateImpl("La La Land", lalaland, R.raw.lalalandpiano),
                StateImpl("Cerro del\nTio Pio", cerrodeltiopio)
        ))

        val repositoryCircularModel = RepositoryStateImplCircularModel(this)
        val model: Model? = CircularModel(states,
                repositoryCircularModel
        )

        val `in` = AnimationUtils.loadAnimation(this, R.anim.fadein)
        val out = AnimationUtils.loadAnimation(this, R.anim.fadeout)

        mainTextView.inAnimation = `in`
        mainTextView.outAnimation = out

        view_background.inAnimation = `in`
        view_background.outAnimation = out

        mainTextView.setFactory {
            val textView = TextView(this@MainActivity)
            textView.textSize = 36f
            textView.gravity = Gravity.CENTER
            textView.setTextColor(Color.WHITE)
            textView.setShadowLayer(30f, 0f, 0f, Color.GRAY)
            textView
        }

        view_background.setFactory {
            val imageView = GifImageView(this@MainActivity)
            imageView.scaleType = ImageView.ScaleType.CENTER_CROP
            imageView.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
            imageView
        }

        presenter = PresenterImpl(this, model!!)
    }

    fun onTouchBackground(view: View) {
        presenter.onClickBackground()
    }

    fun onTouchPreviousThing(view: View) {
        presenter.onClickPreviousButton()
    }

    override fun executeState(state: State) {
        state.execute(this)
    }

    override fun playSong(song: Int) {
        var newSong: MusicHandler? = null
        if (song != 0) {
            newSong = MusicHandler(this)
            newSong.load(song, true)
            newSong.play(50)
        }
        playingSong?.stopAndRelease(fadeDuration)
        playingSong = newSong

    }

    override fun onDestroy() {
        super.onDestroy()
        playingSong?.stopAndRelease()
    }

    override fun onPause() {
        super.onPause()
        playingSong?.pause()
    }

    override fun onResume() {
        super.onResume()
        playingSong?.play()
    }
}
