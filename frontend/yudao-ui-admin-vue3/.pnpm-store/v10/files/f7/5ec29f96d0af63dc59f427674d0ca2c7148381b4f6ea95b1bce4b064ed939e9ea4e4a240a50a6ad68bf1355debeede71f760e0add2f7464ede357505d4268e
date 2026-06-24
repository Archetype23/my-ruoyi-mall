/**
 * @description SelectList class
 * @author wangfupeng
 */
import { IDomEditor } from '../../editor/interface';
import { Dom7Array } from '../../utils/dom';
import { IOption } from '../interface';
import PanelAndModal from './BaseClass';
declare class SelectList extends PanelAndModal {
    type: string;
    readonly $elem: Dom7Array;
    constructor(editor: IDomEditor, width?: number);
    /**
     * 渲染 list
     * @param options select options
     */
    renderList(options: IOption[]): void;
    genSelfElem(): Dom7Array | null;
}
export default SelectList;
