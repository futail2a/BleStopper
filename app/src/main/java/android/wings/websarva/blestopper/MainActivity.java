package android.wings.websarva.blestopper;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
        CompoundButton btButton = (CompoundButton) findViewById(R.id.btButton);
        btButton.setChecked(ba.isEnabled());

        btButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
                //ba.disable();
                Intent intent = new Intent(MainActivity.this, BleObserverService.class);
                if (isChecked) {
                    startService(intent);
                } else {
                    stopService(intent);
                }
            }
        });
    }

}
