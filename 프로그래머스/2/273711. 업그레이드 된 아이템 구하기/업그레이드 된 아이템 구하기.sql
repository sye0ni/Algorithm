select item_info.item_id, item_info.item_name, item_info.rarity from item_info
where item_info.item_id in (
    select it.item_id from item_tree it
    join item_info ii on it.parent_item_id = ii.item_id 
    where ii.rarity='RARE'
) order by item_info.item_id desc;