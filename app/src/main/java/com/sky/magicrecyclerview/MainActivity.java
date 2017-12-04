package com.sky.magicrecyclerview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import com.sky.magicrecyclerview.magicdata.Type1Data;
import com.sky.magicrecyclerview.magicdata.Type2Data;
import com.sky.magicrecyclerview.magicevent.Event1;
import com.wesksky.magicrecyclerview.IMagicEvent;
import com.wesksky.magicrecyclerview.MagicRecyclerView;

/**
 * @author sky
 */
public class MainActivity extends Activity implements MagicRecyclerView.IHandleMagicEvent {

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
        t2.setTitle("this is T2");
        l.add(t2);
        Type1Data t1 = new Type1Data();
        t1.setUrl(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512379742775&di"
                + "=91b87460b8093b668fd1285ad7243c28&imgtype=0&src=http%3A%2F%2Fa.hiphotos.baidu"
                + ".com%2Fimage%2Fpic%2Fitem%2Fa1ec08fa513d2697e9d245b05ffbb2fb4216d8de.jpg");
        l.add(t1);
        t1 = new Type1Data();
        t1.setUrl(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512379742774&di"
                + "=f6ede6782d628265e98856e90a1757b6&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu"
                + ".com%2Fimage%2Fpic%2Fitem%2F29381f30e924b8997be90ce964061d950b7bf60e.jpg");
        l.add(t1);
        t1 = new Type1Data();
        t1.setUrl(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512379742642&di"
                + "=7bf3ec3ced9527b73ccf1405110a149d&imgtype=0&src=http%3A%2F%2Fpic.58pic"
                + ".com%2F58pic%2F15%2F15%2F46%2F42p58PICAIs_1024.jpg");
        l.add(t1);
        t1 = new Type1Data();
        t1.setUrl(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512379742641&di"
                + "=15b7bd20a49e72bc5225b012499ce0e6&imgtype=0&src=http%3A%2F%2Fi-7.vcimg"
                + ".com%2Ftrim%2Fdb7ce8759b021d4c99de021b995358d3636544%2Ftrim.jpg");
        l.add(t1);
        t1 = new Type1Data();
        t1.setUrl(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512379742639&di"
                + "=651aec8ed610596d549ce09057a191e9&imgtype=0&src=http%3A%2F%2Fp3.qhimg"
                + ".com%2Ft014937d1805555fa97.jpg");
        l.add(t1);
        t1 = new Type1Data();
        t1.setUrl(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512379742638&di"
                + "=5c9b472b7e5304152a2f1b0ba68094e7&imgtype=0&src=http%3A%2F%2Fimages.17173.com%2F2015%2Fnews%2F2015"
                + "%2F09%2F14%2Flzy0914sc26s.jpg");
        l.add(t1);
        t1 = new Type1Data();
        t1.setUrl(
            "http://images.17173.com/2014/news/2014/04/04/mj0404bn02s.jpg");
        l.add(t1);
        t1 = new Type1Data();
        t1.setUrl(
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1512379742637&di"
                + "=1b6dc956a8dbd7297ed947cf94079cbd&imgtype=0&src=http%3A%2F%2Fscdn"
                + ".file1.gk99.com%2Fphoto%2F2011-08%2F2011-08-26%2F131432303095289.jpg");
        l.add(t1);

        magicRecyclerView.refresh();
    }

    @Override
    public void post(IMagicEvent event) {
        if (event instanceof Event1) {
            Event1 e = (Event1)event;
            l.remove(e.position);
            Toast.makeText(this, "" + e.position, Toast.LENGTH_LONG).show();
            magicRecyclerView.refresh();
        }
    }
}