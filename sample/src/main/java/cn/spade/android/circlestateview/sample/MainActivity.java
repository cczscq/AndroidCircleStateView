package cn.spade.android.circlestateview.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.cengalabs.flatui.views.FlatButton;
import com.cengalabs.flatui.views.FlatSeekBar;
import com.cengalabs.flatui.views.FlatToggleButton;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;
import cn.spade.android.circlestateview.CircleStateView;
import cn.spade.android.circlestateview.sample.tool.AssetsTool;

public class MainActivity extends AppCompatActivity {


  @InjectView(R.id.circleStateView)
  CircleStateView circleStateView;

  @InjectView(R.id.flatSeekBar)
  FlatSeekBar flatSeekBar;

  @InjectView(R.id.flatToggleButton)
  FlatToggleButton flatToggleButton;

  @InjectView(R.id.flatButton)
  FlatButton flatButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	ButterKnife.inject(this);

	flatButton.setOnClickListener(new View.OnClickListener() {
	  @Override
	  public void onClick(View view) {
		circleStateView.setCsvWholeForegroundColor(R.color.sample_whole_foreground);
		circleStateView.setCsvInnerBackgroundColor(R.color.sample_inner_background);
		circleStateView.setCsvInnerTextColor(R.color.sample_inner_text);
		circleStateView.setCsvStrokeColor(R.color.sample_stroke);
		circleStateView.setCsvTextTypeface(AssetsTool.getCustomizeTypeface(MainActivity.this));
		circleStateView.setCsvInnerText(randomTextId());
		circleStateView.refresh();
	  }
	});

	flatSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
	  @Override
	  public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		circleStateView.setCsvWholeForegroundColor(R.color.sample_whole_foreground);
		circleStateView.setCsvInnerBackgroundColor(R.color.sample_inner_background);
		circleStateView.setCsvInnerTextColor(R.color.sample_inner_text);
		circleStateView.setCsvStrokeColor(R.color.sample_stroke);
		circleStateView.setCsvInnerWaterColor(R.color.sample_inner_water);
		circleStateView.setCsvWaterPgrogress(progress);
		circleStateView.setCsvInnerText(R.string.sample_inner_text_content);
		circleStateView.refresh();
	  }

	  @Override
	  public void onStartTrackingTouch(SeekBar seekBar) {

	  }

	  @Override
	  public void onStopTrackingTouch(SeekBar seekBar) {

	  }
	});


	flatToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
	  @Override
	  public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if (isChecked) {
		  flatSeekBar.setVisibility(View.INVISIBLE);
		  flatButton.setVisibility(View.VISIBLE);

		} else {
		  flatSeekBar.setVisibility(View.VISIBLE);
		  flatButton.setVisibility(View.INVISIBLE);
		  circleStateView.setCsvTextTypeface(null);
		}
	  }
	});
	flatToggleButton.setChecked(true);
	flatToggleButton.setChecked(false);
  }


  private int randomTextId() {
	int result = R.string.font_text_01;
	int random = new Random().nextInt() % 5;
	switch (random) {
	  case 0:
		result = R.string.font_text_01;
		break;
	  case 1:
		result = R.string.font_text_02;
		break;
	  case 2:
		result = R.string.font_text_03;
		break;
	  case 3:
		result = R.string.font_text_04;
		break;
	  case 4:
		result = R.string.font_text_05;
		break;
	}
	return result;
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
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
