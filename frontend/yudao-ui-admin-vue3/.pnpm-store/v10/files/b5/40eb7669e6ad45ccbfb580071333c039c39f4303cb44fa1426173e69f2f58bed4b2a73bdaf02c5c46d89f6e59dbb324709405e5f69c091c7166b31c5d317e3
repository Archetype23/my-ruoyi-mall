/**
 * @description editor video menu
 * @author
 */
import { IDomEditor, IModalMenu } from '@wangeditor-next/core';
import { Node as SlateNode } from 'slate';
import { DOMElement } from '../../utils/dom';
declare class Editvideo implements IModalMenu {
    readonly title: string;
    readonly iconSvg = "<svg viewBox=\"0 0 1024 1024\"><path d=\"M864 0a160 160 0 0 1 128 256l-64 64-224-224 64-64c26.752-20.096 59.968-32 96-32zM64 736l-64 288 288-64 592-592-224-224L64 736z m651.584-372.416l-448 448-55.168-55.168 448-448 55.168 55.168z\"></path></svg>";
    readonly tag = "button";
    readonly showModal = true;
    readonly modalWidth = 300;
    private $content;
    private readonly srcInputId;
    private readonly posterInputId;
    private readonly buttonId;
    private getSelectedVideoNode;
    getValue(_editor: IDomEditor): string | boolean;
    isActive(_editor: IDomEditor): boolean;
    exec(_editor: IDomEditor, _value: string | boolean): void;
    isDisabled(editor: IDomEditor): boolean;
    getModalPositionNode(editor: IDomEditor): SlateNode | null;
    getModalContentElem(editor: IDomEditor): DOMElement;
}
export default Editvideo;
