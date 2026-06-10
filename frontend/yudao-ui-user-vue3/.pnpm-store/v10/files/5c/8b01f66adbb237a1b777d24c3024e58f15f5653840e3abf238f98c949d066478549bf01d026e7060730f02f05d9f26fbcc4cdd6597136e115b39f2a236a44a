import { Editor, Element, Location, NodeEntry } from 'slate';
export declare const TableCursor: {
    /** @returns {boolean} `true` if the selection is inside a table, otherwise `false`. */
    isInTable(editor: Editor, options?: {
        at?: Location;
    }): boolean;
    /**
     * Retrieves a matrix representing the selected cells within a table.
     * @returns {NodeEntry<T>[][]} A matrix containing the selected cells.
     */
    selection(editor: Editor): Generator<NodeEntry[]>;
    /** Clears the selection from the table */
    unselect(editor: Editor): void;
    /**
     * Checks whether a given cell is part of the current table selection.
     * @returns {boolean} - Returns true if the cell is selected, otherwise false.
     */
    isSelected<T extends Element>(editor: Editor, element: T): boolean;
    hasSelected(editor: Editor): boolean;
};
