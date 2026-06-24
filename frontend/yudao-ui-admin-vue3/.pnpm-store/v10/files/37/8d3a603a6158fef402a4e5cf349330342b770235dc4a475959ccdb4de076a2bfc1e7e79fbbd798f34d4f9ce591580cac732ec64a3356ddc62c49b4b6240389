/**
 * @description indent base menu
 * @author wangfupeng
 */
import { IButtonMenu, IDomEditor } from '@wangeditor-next/core';
import { Node } from 'slate';
declare abstract class BaseMenu implements IButtonMenu {
    abstract readonly title: string;
    abstract readonly iconSvg: string;
    readonly tag = "button";
    /**
     * 获取 node.indent 的值，如 `2em`
     * @param editor editor
     */
    getValue(editor: IDomEditor): string | boolean;
    isActive(_editor: IDomEditor): boolean;
    /**
     * 获取 node 节点
     * @param editor editor
     */
    protected getMatchNode(editor: IDomEditor): Node | null;
    abstract isDisabled(editor: IDomEditor): boolean;
    abstract exec(editor: IDomEditor, value: string | boolean): void;
}
export default BaseMenu;
