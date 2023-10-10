package ru.acediat.places

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.squareup.picasso.Picasso
import ru.acediat.places.databinding.ItemPlaceBinding
import ru.acediat.places.entities.Place
import javax.inject.Inject

class PlacesAdapter @Inject constructor(
    private val picasso: Picasso,
) : RecyclerView.Adapter<PlacesAdapter.ViewHolder>() {

    private val items = mutableListOf<Place>()
    private var onItemClick: ((Place) -> Unit)? =null

    @SuppressLint("NotifyDataSetChanged")
    fun setPlaces(items: List<Place>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnClickListener(onClick: (Place) -> Unit) {
        onItemClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPlaceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    override fun getItemCount() = items.size

    inner class ViewHolder(
        private val binding: ItemPlaceBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Place): Unit = with(binding) {
            onItemClick?.let {
                root.setOnClickListener { it(item) }
            }
            title.text = item.title
            description.text = item.description
            Log.d("adapter", item.toString())
            picasso.load(item.photos[0])
                .fit()
                .centerCrop()
                .into(backgroundImage)
        }
    }
}