export default function getSlot(slots, exclude) {
    return Object.keys(slots).reduce((lst, name) => {
        if (!exclude || exclude.indexOf(name) === -1) {
            lst[name] = slots[name]
        }
        return lst;
    }, {})
}