package com.developer.kamalasekar.quiz;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RatingBar;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * This a mainActivity calls the app launches the activity_main and then the corresponding method is called
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The components from the Activity main is been addressed here
     */
    Activity context;
    TextView txtview;
    ProgressDialog pd;
    RatingBar ratingBar;
    Button retake;
    ArrayList<String> stringList = new ArrayList<String>();
    static int questionNum = 0;
    private RadioGroup radioQuestions;
    private RadioButton radioButton;
    public static int count;
    ImageView image;

    /**
     * This method is used to launch the activity and set the paramter as mentioned below
     * The retake test button is set as gone
     * The rating bar is set as gone
     * The background task will be initilized here so that the data from the papa server can be retrived here
     * This retrival of data is done as a asynctak
     * The intiall loading is alos ben set here
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);                                                     // To launch Activity_Main
        context=this;
        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        retake=(Button) findViewById(R.id.retake);
        ratingBar.setVisibility(View.GONE);                                                         //Set retake bar out of visibility
        retake.setVisibility(View.GONE);                                                            //Set retake bar out of visibility
        BackgroundTask bt = new BackgroundTask();
        bt.execute("http://www.papademas.net/sample.txt");                                          //grab url

    }//end onCreate

    //background process to download the file from internet
    public class BackgroundTask extends AsyncTask<String, Integer, Void> {

        protected void onPreExecute() {
            super.onPreExecute();
            //display progress dialog
            pd = new ProgressDialog(context);
            pd.setTitle("Reading the text file");                                                   //The title set while retrieving data from the papa server
            pd.setMessage("Please wait.");
            pd.setCancelable(true);
            pd.setIndeterminate(false);
            pd.show();

        }

        protected Void doInBackground(String... params) {
            URL url;
            String StringBuffer = null;
            try {
                //create url object to point to the file location on internet
                url = new URL(params[0]);
                //make a request to server
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                //get InputStream instance
                InputStream is = con.getInputStream();
                //create BufferedReader object
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                //read content of the file line by line & add it to Stringbuffer
                while ((StringBuffer = br.readLine()) != null) {
                    stringList.add(StringBuffer);  //add to Arraylist
                }

                br.close();

            } catch (Exception e) {
                e.printStackTrace();
                //close dialog if error occurs
                if (pd != null) pd.dismiss();
            }

            return null;

        }

        protected void onPostExecute(Void result) {
            //close dialog
            if (pd != null)
                pd.dismiss();
            txtview = (TextView) findViewById(R.id.textView1);
            //display read text in TextVeiw
            txtview.setText(stringList.get(0));
            System.out.print("Im now before the startquiz");
            //Log.i("I'm now before the startQuiz","");
            startQuiz();


        }
    }//end BackgroundTask class

    /**
     * This method initiates the process and switch on the button listener method
     */
    public void startQuiz() {
        System.out.print("Im now In the startquiz");
        buttonListener();
    }

    /**
     * This method consist of the Display button listener
     * The radio button data is grasped and then the answer is judged here.
     * Switch statement is used to identify the question answer
     * In first Four cases when display result is pressed the screen provides the toast message
     * In the fifth question when the display button is pressed the screen show the toast message and then the rating is displayed
     */
    public void buttonListener() {

        System.out.print("Im now In the ButtonListner");
        //Log.i("Im now In the ButtonListner",+count);

        Button btnDisplay;
        count=0;

        radioQuestions = (RadioGroup) findViewById(R.id.radioQuestions);
        btnDisplay = (Button) findViewById(R.id.btnDisplay);

        btnDisplay.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                // get selected radio button from radioGroup
                int selectedId = radioQuestions.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                radioButton = (RadioButton) findViewById(selectedId);

                switch (questionNum) {
                    case 0:
                        //verify if result matches the right button selection
                        //i.e., (True or false!)
                        if (radioButton.getText().equals("True")){
                            Toast.makeText(MainActivity.this,
                                    " Right!", Toast.LENGTH_SHORT).show();
                            //count=count+1;
                        }
                        else
                            Toast.makeText(MainActivity.this,
                                    " Wrong!", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        //verify if result matches the right button selection
                        //i.e., (True or false!)
                        if (radioButton.getText().equals("False")){
                            Toast.makeText(MainActivity.this,
                                    " Right!", Toast.LENGTH_SHORT).show();
                            //count=count+1;
                        }
                        else
                            Toast.makeText(MainActivity.this,
                                    " Wrong!", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        //verify if result matches the right button selection
                        //i.e., (True or false!)
                        if (radioButton.getText().equals("True")) {
                            Toast.makeText(MainActivity.this,
                                    " Right!", Toast.LENGTH_SHORT).show();
                            //count=count+1;
                        }
                        else
                            Toast.makeText(MainActivity.this,
                                    " Wrong!", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        //verify if result matches the right button selection
                        //i.e., (True or false!)
                        if (radioButton.getText().equals("False")){
                            Toast.makeText(MainActivity.this,
                                    " Right!", Toast.LENGTH_LONG).show();
                            //count=count+1;
                        }
                        else
                            Toast.makeText(MainActivity.this,
                                    " Wrong!", Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        //verify if result matches the right button selection
                        //i.e., (True or false!)
                        if (radioButton.getText().equals("False")){
                            Toast.makeText(MainActivity.this,
                                    " Right!", Toast.LENGTH_SHORT).show();
                            if (count <=4) {
                                count = count + 1;
                                Log.i("Im in Diaplay Result",String.valueOf(count));
                            }

                            if (count == 5) {
                                View layout = (View) findViewById(R.id.layout);
                                layout.setBackgroundColor(Color.GREEN);
                            }
                            // The rating bar is been displayed
                            ratingBar = (RatingBar) findViewById(R.id.ratingBar);
                            ratingBar.setRating(count);
                            Drawable drawable = ratingBar.getProgressDrawable();
                            drawable.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_ATOP);
                            ratingBar.setVisibility(View.VISIBLE);
                        }
                        else {
                            Toast.makeText(MainActivity.this,
                                    " Wrong!", Toast.LENGTH_SHORT).show();
                            ratingBar = (RatingBar) findViewById(R.id.ratingBar);
                            ratingBar.setRating(count);
                            ratingBar.setVisibility(View.VISIBLE);
                        }

                        break;
                    //finish switch cases 2-4
                }//end switch
            }
        });
        imageListener();
    }//end buttonListener

    /**
     * This method is used for the next question button in the screen
     * On click of this button the textview is been set for the next question
     * The answer status is been read and then the count is increased
     * At the end of the last question the retake test button is been displayed
     * On click of this retake test button the test is been started over fresh
     *      */
    public void imageListener() {


        System.out.print("Im now In the ImageListner");
        //Log.i("Im now In the Listner","");

        image = (ImageView) findViewById(R.id.imageView1);
        image.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // get new question for viewing
                if (questionNum == 4) {
                    int selectedId = radioQuestions.getCheckedRadioButtonId();
                    radioButton = (RadioButton) findViewById(selectedId);
                    if (radioButton.getText().equals("True")){
                        Log.i("Question 5:","Wrong Answer");
                    }
                    else{
                        count=count+1;
                        Log.isLoggable("In case5",count);
                        Log.i("I'min case 5", String.valueOf(count));
                    }
                    image.setVisibility(View.GONE);
//                    ratingBar = (RatingBar) findViewById(R.id.ratingBar);
//                    ratingBar.setVisibility(View.VISIBLE);
                    ratingBar.setRating(count);
                    retake.setVisibility(View.VISIBLE);

                    /**
                     * The button is used to set the content of teh test from teh beginning
                     */

                    retake.setOnClickListener(new OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            //startQuiz();
                            View layout = (View) findViewById(R.id.layout);
                            layout.setBackgroundColor(Color.WHITE);
                            count=0;
                            questionNum = -1;
                            txtview.setText(stringList.get(++questionNum));

                            Log.i("After increament", String.valueOf(questionNum));
                            //reset radio button (radioTrue) to default
                            radioQuestions.check(R.id.radioTrue);
                            ratingBar.setVisibility(View.GONE);
                            retake.setVisibility(View.GONE);
                            image.setVisibility(View.VISIBLE);
                        }
                        });

                            if (count == 5) {
                                View layout = (View) findViewById(R.id.layout);
                                layout.setBackgroundColor(Color.GREEN);
                                 }
                            //reset count to -1 to start first question again
                            //questionNum = -1;
                        }

                        else

                        {
                            int selectedId = radioQuestions.getCheckedRadioButtonId();
                            radioButton = (RadioButton) findViewById(selectedId);
                            //Log.i("Before increament", String.valueOf(questionNum));
                            switch (questionNum) {
                                case 0:
                                    //verify if result matches the right button selection
                                    //i.e., (True or false!)
                                    if (radioButton.getText().equals("True")) {
                                        count = count + 1;
                                        Log.isLoggable("In case1", count);
                                        Log.i("I'min case 1", String.valueOf(count));
                                    }
                                    break;
                                case 1:
                                    //verify if result matches the right button selection
                                    //i.e., (True or false!)
                                    if (radioButton.getText().equals("True")) {


                                        Log.i("I'min case 2", "wrong Answer");
                                    } else {
                                        count = count + 1;
                                        Log.i("In case2", String.valueOf(count));
                                    }

                                    break;
                                case 2:
                                    //verify if result matches the right button selection
                                    //i.e., (True or false!)
                                    if (radioButton.getText().equals("True")) {

                                        count = count + 1;
                                        Log.isLoggable("In case3", count);
                                        Log.i("I'min case 3", String.valueOf(count));
                                    }

                                    break;
                                case 3:
                                    //verify if result matches the right button selection
                                    //i.e., (True or false!)
                                    if (radioButton.getText().equals("True")) {
                                        Log.i("Question 4:", "Wrong Answer");
                                    } else {
                                        count = count + 1;
                                        Log.isLoggable("In case4", count);
                                        Log.i("I'min case 4", String.valueOf(count));
                                    }

                                    break;
                            }//end switch

                            txtview.setText(stringList.get(++questionNum));

                            //Log.i("After increament", String.valueOf(questionNum));
                            //reset radio button (radioTrue) to default
                            radioQuestions.check(R.id.radioTrue);


                        }
                    }
                });
    }//end imageListener

}//end activity
