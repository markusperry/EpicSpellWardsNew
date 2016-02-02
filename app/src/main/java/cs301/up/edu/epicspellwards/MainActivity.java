package cs301.up.edu.epicspellwards;

/**
 * @author Markus Perry, Elizabeth Mukai, Scott Zimmerman, Teresa Condon.
 * =====EVENTS IMPLEMENTED=======
 * -When user taps cards in hand they become highlighted and return to normal
 *      upon clicking again
 * -When Ready button is clicked all unselected cards go poof.
 * -When mute button is pressed the volume control and view go poof
 * -Slider controls volume displayed in adjacent Text View
 *
 */

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    //instance variables
    protected ImageButton card1;
    protected ImageButton card;
    protected ImageButton card2;
    protected ImageButton card3;
    protected ImageButton card4;
    protected ImageButton card5;
    protected ImageButton card6;
    protected ImageButton card7;
    protected ImageButton card8;
    protected Button ready;
    protected SeekBar volumeBar;
    protected TextView volume;
    protected Button mute;

    protected ImageButton[] cardHands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // creates the spinner Menu
        String[] menu = {"Menu","Return to Game","Quit to Home Screen","Settings"};

        //Instantiates Spinner
        Spinner mSpinner = (Spinner)findViewById(R.id.menuSpinner);
        ArrayAdapter menus = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, menu);
        mSpinner.setAdapter(menus);

        // instantiate the buttons with listeners
        ready = (Button)findViewById(R.id.readyButton);
        ready.setOnClickListener(this);

        card1 = (ImageButton)findViewById(R.id.card1Button);
        card1.setOnClickListener(this);

        card2 = (ImageButton)findViewById(R.id.card2Button);
        card2.setOnClickListener(this);

        card3 = (ImageButton)findViewById(R.id.card3Button);
        card3.setOnClickListener(this);

        card4 = (ImageButton)findViewById(R.id.card4Button);
        card4.setOnClickListener(this);

        card5 = (ImageButton)findViewById(R.id.card5Button);
        card5.setOnClickListener(this);

        card6 = (ImageButton)findViewById(R.id.card6Button);
        card6.setOnClickListener(this);

        card7 = (ImageButton)findViewById(R.id.card7Button);
        card7.setOnClickListener(this);

        card8 = (ImageButton)findViewById(R.id.card8Button);
        card8.setOnClickListener(this);

        //Array of all cards in hand
        cardHands = new ImageButton[8];

        // instantiate the volume seekBar and its TextView
        volumeBar = (SeekBar)findViewById(R.id.volumeControl);
        volumeBar.setOnSeekBarChangeListener(this);

        volume = (TextView)findViewById(R.id.volumeView);

        // instantiate the muteButton
        mute = (Button)findViewById(R.id.muteButton);
        mute.setOnClickListener(this);

        // fill the cardHands array
        cardHands[0] = card1;
        cardHands[1] = card2;
        cardHands[2] = card3;
        cardHands[3] = card4;
        cardHands[4] = card5;
        cardHands[5] = card6;
        cardHands[6] = card7;
        cardHands[7] = card8;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * method called whenever user clicks on the screen
     * @param v     View that was clicked by user
     */
    @Override
    public void onClick(View v) {

        // If readyButton was pressed, all non- clicked cards vanish (poof!)
        if (v.getId() == R.id.readyButton)
        {
            for(int i = 0; i < 8; i++) {
                Log.i("blah", "" + cardHands[i].getImageAlpha());
                if (cardHands[i].getImageAlpha() > 250) {
                    cardHands[i].setVisibility(View.INVISIBLE);
                    ready.setVisibility(View.INVISIBLE);
                }
            }
        }
        // If muteButton was pressed, the volume seekbar and text field vanish (poof!)
        else if (v.getId()==R.id.muteButton)
        {
            if (this.volumeBar.getVisibility()==View.INVISIBLE)
            {
                this.volumeBar.setVisibility(View.VISIBLE);
                this.volume.setVisibility(View.VISIBLE);
            }
            else {
                this.volumeBar.setVisibility(View.INVISIBLE);
                this.volume.setVisibility(View.INVISIBLE);
            }
        }
        // shades the clicked cards
        else {
            card = (ImageButton) findViewById(v.getId());
            card.setImageAlpha(382 - card.getImageAlpha());
        }



        v.invalidate();
    }

    /**
     * method that changes the seekbar
     *
     * @param seekBar   the seekbar that user drags
     * @param progress  the int value that seekBar is set to
     * @param fromUser  unused
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        volume.setText(""+progress);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    // unused
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    // unused
    }
}
