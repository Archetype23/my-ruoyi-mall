/**
 * @description set cell property menu
 * @author hsuna
 */
import { IButtonMenu, IDomEditor } from '@wangeditor-next/core';
declare class TableProperty implements IButtonMenu {
    readonly title: string;
    iconSvg: string;
    readonly tag = "button";
    readonly showModal = true;
    readonly modalWidth = 300;
    readonly menu: string;
    readonly borderStyle: {
        value: string;
        label: string;
    }[];
    readonly textAlignOptions: {
        value: string;
        label: string;
        svg: string;
    }[];
    getValue(_editor: IDomEditor): string | boolean;
    isActive(_editor: IDomEditor): boolean;
    isDisabled(editor: IDomEditor): boolean;
    exec(_editor: IDomEditor, _value: string | boolean): void;
    getModalContentNode(editor: IDomEditor): import("slate").NodeEntry<import("../../utils").WithType<import("packages/custom-types").CustomElement>>;
    getModalPositionNode(_editor: IDomEditor): null;
    getModalContentElem(editor: IDomEditor): Element | null;
    getPanelContentElem(editor: any, { mark, selectedColor, callback }: {
        mark: any;
        selectedColor: any;
        callback: any;
    }): import("dom7").Dom7Array;
}
export default TableProperty;
