package com.sky.magicrecyclerview;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.sky.magicrecyclerview.magicdata.Type1Data;
import com.sky.magicrecyclerview.magicdata.Type2Data;
import com.sky.magicrecyclerview.magicevent.Event1;
import com.wesksky.magicrecyclerview.IMagicEvent;
import com.wesksky.magicrecyclerview.MagicRecyclerView;

/**
 * @author sky
 */
public class MainActivity extends AppCompatActivity implements MagicRecyclerView.IHandleMagicEvent {

    MagicRecyclerView magicRecyclerView;
    List l = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        magicRecyclerView = (MagicRecyclerView)findViewById(R.id.mrv);
        magicRecyclerView.setData(l);
        magicRecyclerView.setIHandleMagicEvent(this);

        addData();
    }

    private void addData() {
        Type2Data t2 = new Type2Data();
        l.add(t2);
        Type1Data t1 = new Type1Data();
        l.add(t1);
        t1 = new Type1Data();
        l.add(t1);
        t1 = new Type1Data();
        l.add(t1);
        t1 = new Type1Data();
        l.add(t1);
        t1 = new Type1Data();
        l.add(t1);
        t1 = new Type1Data();
        l.add(t1);
        t1 = new Type1Data();
        l.add(t1);
        t1 = new Type1Data();
        l.add(t1);

        t1.setUrl(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511849743569&di"
                + "=be7ebddf529529e64851b7951cdbebf7&imgtype=0&src=http%3A%2F%2Fpic26.nipic"
                + ".com%2F20121224%2F4860403_154152727000_2.jpg");
        t2.setTitle("this is T2");

        magicRecyclerView.refresh();
    }

    @Override
    public void post(IMagicEvent event) {
        if (event instanceof Event1) {
            Event1 e = (Event1)event;
            l.remove(e.position);
            Toast.makeText(this, "", Toast.LENGTH_LONG).show();
            magicRecyclerView.refresh();
        }
    }
}