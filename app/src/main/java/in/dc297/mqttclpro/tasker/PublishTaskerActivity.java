package in.dc297.mqttclpro.tasker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import in.dc297.mqttclpro.R;

public class PublishTaskerActivity extends AbstractPluginActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish_tasker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String topic = getIntent().getStringExtra(in.dc297.mqttclpro.tasker.Intent.EXTRA_TOPIC);
        final String message = getIntent().getStringExtra(in.dc297.mqttclpro.tasker.Intent.EXTRA_MESSAGE);
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        if (null == savedInstanceState)
        {
            if(message!=null && message!="") {
                ((EditText) findViewById(R.id.editText)).setText(message);
            }
            if(topic!=null && topic!="") {
                ((EditText) findViewById(R.id.editText2)).setText(topic);
            }
        }
    }

    @Override
    public void finish()
    {
        if (!isCanceled())
        {
            final String topic = ((EditText) findViewById(R.id.editText)).getText().toString();
            final String message = ((EditText) findViewById(R.id.editText2)).getText().toString();

            if (message.length() > 0 && topic.length() > 0)
            {
                final Intent resultIntent = new Intent();



                resultIntent.putExtra(in.dc297.mqttclpro.tasker.Intent.EXTRA_TOPIC, topic);
                resultIntent.putExtra(in.dc297.mqttclpro.tasker.Intent.EXTRA_MESSAGE, message);
                /*
                 * The blurb is concise status text to be displayed in the host's UI.
                 */
                final String blurb = generateBlurb(getApplicationContext(), topic+" : "+message);
                resultIntent.putExtra(in.dc297.mqttclpro.tasker.Intent.EXTRA_STRING_BLURB, blurb);

                setResult(RESULT_OK, resultIntent)  ;
            }
        }

        super.finish();
    }

    static String generateBlurb(final Context context, final String message)
    {
        final int maxBlurbLength =
                context.getResources().getInteger(R.integer.max_blurb_length);

        if (message.length() > maxBlurbLength)
        {
            return message.substring(0, maxBlurbLength);
        }

        return message;
    }

}
