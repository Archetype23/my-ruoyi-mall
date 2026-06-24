/**
 * @description 修改视频尺寸
 * @author wangfupeng
 */
import { IDomEditor, IModalMenu } from '@wangeditor-next/core';
import { Node as SlateNode } from 'slate';
import { DOMElement } from '../../utils/dom';
declare class EditorVideoSizeMenu implements IModalMenu {
    readonly title: string;
    readonly tag = "button";
    readonly showModal = true;
    readonly modalWidth = 320;
    private $content;
    private readonly widthInputId;
    private readonly heightInputId;
    private readonly buttonId;
    private getSelectedVideoNode;
    getValue(_editor: IDomEditor): string | boolean;
    isActive(_editor: IDomEditor): boolean;
    exec(_editor: IDomEditor, _value: string | boolean): void;
    isDisabled(editor: IDomEditor): boolean;
    getModalPositionNode(editor: IDomEditor): SlateNode | null;
    getModalContentElem(editor: IDomEditor): DOMElement;
}
export default EditorVideoSizeMenu;
