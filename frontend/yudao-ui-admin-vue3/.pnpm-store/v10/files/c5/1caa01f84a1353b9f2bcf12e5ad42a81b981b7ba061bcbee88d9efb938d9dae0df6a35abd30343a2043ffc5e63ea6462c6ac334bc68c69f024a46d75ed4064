/**
 * @description redo menu
 * @author wangfupeng
 */
import { IButtonMenu, IDomEditor } from '@wangeditor-next/core';
declare class RedoMenu implements IButtonMenu {
    title: string;
    iconSvg: string;
    tag: string;
    getValue(_editor: IDomEditor): string | boolean;
    isActive(_editor: IDomEditor): boolean;
    isDisabled(editor: IDomEditor): boolean;
    exec(editor: IDomEditor, _value: string | boolean): void;
}
export default RedoMenu;
