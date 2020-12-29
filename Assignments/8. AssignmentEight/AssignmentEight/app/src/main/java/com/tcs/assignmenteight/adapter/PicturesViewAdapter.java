package com.tcs.assignmenteight.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.tcs.assignmenteight.MainActivity;
import com.tcs.assignmenteight.R;
import com.tcs.assignmenteight.interfaces.AdapterToActivity;

import java.util.ArrayList;
import java.util.List;
public class  PicturesViewAdapter extends RecyclerView.Adapter<PicturesViewAdapter.PicturesViewHolder> {
    List<String> pictures;
    private AdapterToActivity adapterToActivity;
    Context context;

    public PicturesViewAdapter(Context context) {
        this.context = context;
        try {
            adapterToActivity = (AdapterToActivity) context;
        } catch (ClassCastException castException) {
            Log.d("PicturesViewAdapter", "Activity doesn't implement AdapterToActivity");
        }
        pictures = new ArrayList<String>();
        pictures.add("https://image.tmdb.org/t/p/w780/xfWac8MTYDxujaxgPVcRD9yZaul.jpg");
        pictures.add("https://image.tmdb.org/t/p/w780/4Iu5f2nv7huqvuYkmZvSPOtbFjs.jpg");
        pictures.add("https://image.tmdb.org/t/p/w600_and_h900_bestv2/h8Rb9gBr48ODIwYUttZNYeMWeUU.jpg");
        pictures.add("https://image.tmdb.org/t/p/w780/4ynQYtSEuU5hyipcGkfD6ncwtwz.jpg");
        pictures.add("https://image.tmdb.org/t/p/w780/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg");
        pictures.add("https://image.tmdb.org/t/p/w780/wwemzKWzjKYJFfCeiB57q3r4Bcm.png");
        pictures.add("https://image.tmdb.org/t/p/w600_and_h900_bestv2/di1bCAfGoJ0BzNEavLsPyxQ2AaB.jpg");
        pictures.add("https://image.tmdb.org/t/p/w600_and_h900_bestv2/iiZZdoQBEYBv6id8su7ImL0oCbD.jpg");
        pictures.add("https://image.tmdb.org/t/p/w600_and_h900_bestv2/k68nPLbIST6NP96JmTxmZijEvCA.jpg");
        pictures.add("https://image.tmdb.org/t/p/w600_and_h900_bestv2/gtwqIYSOCRwEndZTg9s6iWjEZc1.jpg");
    }

    @NonNull
    @Override
    public PicturesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.picture_card, parent, false);
        return new PicturesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PicturesViewAdapter.PicturesViewHolder holder, int position) {
        String path = pictures.get(position);
        holder.pictureId.setText(String.valueOf(position));
        Picasso.get().load(path).into(holder.picture);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public class PicturesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView picture;
        TextView pictureId;
        public PicturesViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            picture = (ImageView) itemView.findViewById(R.id.imageViewPicture);
            pictureId = (TextView) itemView.findViewById(R.id.textViewPictureName);
        }

        @Override
        public void onClick(View v) {
            TextView pictureId = v.findViewById(R.id.textViewPictureName);
            String name = pictureId.getText().toString();
            adapterToActivity.showMoreDetails(pictures.get(Integer.parseInt(name)));
            }
        }

}
