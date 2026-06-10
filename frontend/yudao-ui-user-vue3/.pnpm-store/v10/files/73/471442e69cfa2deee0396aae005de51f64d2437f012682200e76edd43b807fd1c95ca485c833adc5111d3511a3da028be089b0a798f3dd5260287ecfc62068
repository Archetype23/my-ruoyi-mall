/**
 * @description image width base class
 * @author wangfupeng
 */
import { IButtonMenu, IDomEditor } from '@wangeditor-next/core';
declare abstract class ImageWidthBaseClass implements IButtonMenu {
    abstract readonly title: string;
    readonly tag = "button";
    abstract readonly value: string;
    getValue(_editor: IDomEditor): string | boolean;
    isActive(_editor: IDomEditor): boolean;
    private getSelectedNode;
    isDisabled(editor: IDomEditor): boolean;
    exec(editor: IDomEditor, _value: string | boolean): void;
}
export default ImageWidthBaseClass;
