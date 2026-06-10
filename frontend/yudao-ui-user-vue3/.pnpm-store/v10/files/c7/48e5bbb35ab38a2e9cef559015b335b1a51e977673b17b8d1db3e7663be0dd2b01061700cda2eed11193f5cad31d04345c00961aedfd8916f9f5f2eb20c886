/**
 * @description justify base menu
 * @author wangfupeng
 */
import { IButtonMenu, IDomEditor } from '@wangeditor-next/core';
declare abstract class BaseMenu implements IButtonMenu {
    abstract readonly title: string;
    abstract readonly iconSvg: string;
    readonly tag = "button";
    getValue(_editor: IDomEditor): string | boolean;
    isActive(_editor: IDomEditor): boolean;
    isDisabled(editor: IDomEditor): boolean;
    abstract exec(editor: IDomEditor, value: string | boolean): void;
}
export default BaseMenu;
