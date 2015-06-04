package cn.spade.android.circlestateview.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.spade.android.circlestateview.CircleStateView;

public class MainActivity extends AppCompatActivity {


  @InjectView(R.id.circleStateView)
  CircleStateView circleStateView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	ButterKnife.inject(this);

    circleStateView.setCsvWholeForegroundColor(R.color.sample_whole_foreground);
    circleStateView.setCsvInnerBackgroundColor(R.color.sample_inner_background);
    circleStateView.setCsvInnerTextColor(R.color.sample_inner_text);
    circleStateView.setCsvStrokeColor(R.color.sample_stroke);
    circleStateView.setCsvInnerWaterColor(R.color.sample_inner_water);
    circleStateView.setCsvWaterPgrogress(30);
    circleStateView.setCsvInnerText(R.string.sample_inner_text_content);
    circleStateView.refresh();
  }



  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.menu_main, menu);
	return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
	int id = item.getItemId();

	if (id == R.id.action_settings) {
	  return true;
	}

	return super.onOptionsItemSelected(item);
  }
}
