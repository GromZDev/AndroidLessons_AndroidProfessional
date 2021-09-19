package q4_android_professional.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import q4_android_professional.myapplication.databinding.ItemMainFragmentRvBinding

class MainAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var data: List<DataModel>,
    val imageLoader: ImageLoader<ShapeableImageView>
) :
    RecyclerView.Adapter<MainAdapter.RecyclerItemViewHolder>() {


    fun setData(data: List<DataModel>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        RecyclerItemViewHolder(
            ItemMainFragmentRvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )

    override fun onBindViewHolder(holder: RecyclerItemViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class RecyclerItemViewHolder(vb: ItemMainFragmentRvBinding) :
        RecyclerView.ViewHolder(vb.root) {


        fun bind(data: DataModel) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.header_textview_recycler_item).text = data.text

                itemView.findViewById<TextView>(R.id.description_textview_recycler_item).text =
                    data.meanings?.get(0)?.translation?.translation

                imageLoader.loadInto(
                    data.meanings?.get(0)?.imageUrl.toString(),
                    itemView.findViewById(R.id.item_word_image)
                )

                itemView.findViewById<TextView>(R.id.transcription_textview_recycler_item).text =
                    data.meanings?.get(0)?.transcription

                itemView.setOnClickListener { openInNewWindow(data) }
            }
        }
    }

    private fun openInNewWindow(listItemData: DataModel) {
        onListItemClickListener.onItemClick(listItemData)
    }

    interface OnListItemClickListener {
        fun onItemClick(data: DataModel)
    }

}