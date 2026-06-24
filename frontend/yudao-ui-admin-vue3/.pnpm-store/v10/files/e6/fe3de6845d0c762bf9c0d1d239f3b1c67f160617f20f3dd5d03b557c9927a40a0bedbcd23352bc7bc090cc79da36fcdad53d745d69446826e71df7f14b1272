/**
 * @description redo menu
 * @author wangfupeng
 */
import { IButtonMenu, IDomEditor } from '@wangeditor-next/core';
declare class FullScreen implements IButtonMenu {
    title: string;
    iconSvg: string;
    tag: string;
    alwaysEnable: boolean;
    getValue(_editor: IDomEditor): string | boolean;
    isActive(editor: IDomEditor): boolean;
    isDisabled(_editor: IDomEditor): boolean;
    getIcon(editor: IDomEditor): string;
    getTitle(editor: IDomEditor): string;
    exec(editor: IDomEditor, _value: string | boolean): void;
}
export default FullScreen;
