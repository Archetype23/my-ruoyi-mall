import { IButtonMenu, IDomEditor } from '@wangeditor-next/core';
import { Editor } from 'slate';
declare class SplitCell implements IButtonMenu {
    readonly title: string;
    readonly iconSvg = "<svg viewBox=\"0 0 1024 1024\"><path d=\"M362.667 494.933v53.334l25.6-25.6zm0-241.066L460.8 352V78.933H57.6v98.134h305.067zm0 535.466v57.6H57.6v98.134h403.2V691.2zM661.333 494.933v53.334l-25.6-25.6zm0-241.066L563.2 352V78.933h403.2v98.134H661.333zm0 535.466v57.6H966.4v98.134H563.2V691.2z\"/><path d=\"M753.067 341.333 693.333 281.6 512 460.8 330.667 281.6l-59.734 59.733 181.334 181.334L270.933 704l59.734 59.733L512 582.4l181.333 181.333L753.067 704 571.733 522.667z\"/></svg>";
    readonly tag = "button";
    getValue(_editor: IDomEditor): string | boolean;
    isActive(_editor: IDomEditor): boolean;
    isDisabled(editor: IDomEditor): boolean;
    exec(editor: IDomEditor, _value: string | boolean): void;
    /**
     * Splits either the cell at the current selection or a specified location. If a range
     * selection is present, all cells within the range will be split.
     * @param {Location} [options.at] - Splits the cell at the specified location. If no
     * location is specified it will split the cell at the current selection
     * @param {boolean} [options.all] - If true, splits all cells in the table
     * @returns void
     */
    split(editor: Editor, options?: {
        at?: Location;
        all?: boolean;
    }): void;
}
export default SplitCell;
