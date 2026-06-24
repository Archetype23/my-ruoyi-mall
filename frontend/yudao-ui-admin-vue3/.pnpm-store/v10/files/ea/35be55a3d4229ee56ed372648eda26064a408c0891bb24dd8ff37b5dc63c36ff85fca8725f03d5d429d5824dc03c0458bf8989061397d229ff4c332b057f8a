/**
 * @description insert table menu
 * @author wangfupeng
 */
import { IDomEditor, IDropPanelMenu } from '@wangeditor-next/core';
import { DOMElement } from '../../utils/dom';
declare class InsertTable implements IDropPanelMenu {
    title: string;
    iconSvg: string;
    tag: string;
    showDropPanel: boolean;
    private $content;
    getValue(_editor: IDomEditor): string | boolean;
    isActive(_editor: IDomEditor): boolean;
    exec(_editor: IDomEditor, _value: string | boolean): void;
    isDisabled(editor: IDomEditor): boolean;
    /**
     *  获取 panel 内容
     * @param editor editor
     */
    getPanelContentElem(editor: IDomEditor): DOMElement;
    private insertTable;
}
export default InsertTable;
