import { IButtonMenu, IDomEditor } from '@wangeditor-next/core';
import { Editor } from 'slate';
declare class MergeCell implements IButtonMenu {
    readonly title: string;
    readonly iconSvg = "<svg viewBox=\"0 0 1024 1024\"><path d=\"M482.2 508.4 331.3 389c-3-2.4-7.3-.2-7.3 3.6V478H184V184h204v128c0 2.2 1.8 4 4 4h60c2.2 0 4-1.8 4-4V144c0-15.5-12.5-28-28-28H144c-15.5 0-28 12.5-28 28v736c0 15.5 12.5 28 28 28h284c15.5 0 28-12.5 28-28V712c0-2.2-1.8-4-4-4h-60c-2.2 0-4 1.8-4 4v128H184V546h140v85.4c0 3.8 4.4 6 7.3 3.6l150.9-119.4c2.4-1.8 2.4-5.4 0-7.2zM880 116H596c-15.5 0-28 12.5-28 28v168c0 2.2 1.8 4 4 4h60c2.2 0 4-1.8 4-4V184h204v294H700v-85.4c0-3.8-4.3-6-7.3-3.6l-151 119.4c-2.3 1.8-2.3 5.3 0 7.1l151 119.5c2.9 2.3 7.3.2 7.3-3.6V546h140v294H636V712c0-2.2-1.8-4-4-4h-60c-2.2 0-4 1.8-4 4v168c0 15.5 12.5 28 28 28h284c15.5 0 28-12.5 28-28V144c0-15.5-12.5-28-28-28z\"/></svg>";
    readonly tag = "button";
    getValue(_editor: IDomEditor): string | boolean;
    isActive(_editor: IDomEditor): boolean;
    isDisabled(editor: IDomEditor): boolean;
    exec(editor: IDomEditor, _value: string | boolean): void;
    /**
     * Checks if the current selection can be merged. Merging is not possible when any of the following conditions are met:
     * - The selection is empty.
     * - The selection is not within the same "thead", "tbody," or "tfoot" section.
     * @returns {boolean} `true` if the selection can be merged, otherwise `false`.
     */
    canMerge(editor: Editor): boolean;
    /**
     * Merges the selected cells in the table.
     * @returns void
     */
    merge(editor: Editor): void;
}
export default MergeCell;
