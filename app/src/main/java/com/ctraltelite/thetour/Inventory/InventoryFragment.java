package com.ctraltelite.thetour.Inventory;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.ctraltelite.thetour.Game.Item;
import com.ctraltelite.thetour.MainActivity;
import com.ctraltelite.thetour.R;

import java.util.List;

/**
 * Created by jshodd on 4/11/16.
 */
public class InventoryFragment extends Fragment {

    private RecyclerView mInvRecyclerView;
    private ItemAdapter mAdapter;
    public InventoryFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_inventory, container,false);

        mInvRecyclerView = (RecyclerView) view.findViewById(R.id.inventory_recycler_view);
        mInvRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }
    @Override
    public void onResume(){
        super.onResume();
        updateUI();
    }

    public void updateUI(){
        List<Item> items = MainActivity.getGame().getPlayer().getInventory();

        if (mAdapter==null){
            mAdapter = new ItemAdapter(items);
            mInvRecyclerView.setAdapter(mAdapter);
        }else{
            mAdapter.notifyDataSetChanged();
        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder{
        private Item mItem;
        private TextView mItemNameTextView;
        private Button mItemButton;

        public ItemHolder(View itemView){
            super(itemView);

            mItemNameTextView = (TextView) itemView.findViewById(R.id.Item_name_text_view);
            mItemButton = (Button) itemView.findViewById(R.id.Item_Button);
        }

        public void bindItem(Item item){
            mItem = item;
            mItemNameTextView.setText(mItem.getDescription());
            mItemButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.getGame().dropItem(mItem);
                    updateUI();
                }
            });
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder>{
        private List<Item> mItems;
        public ItemAdapter(List<Item> items){
            mItems = items;
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_inventory_item, parent, false);
            return new ItemHolder(view);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position){
            Item item = mItems.get(position);
            holder.bindItem(item);
        }

        @Override
        public int getItemCount(){return mItems.size();}
    }

}
