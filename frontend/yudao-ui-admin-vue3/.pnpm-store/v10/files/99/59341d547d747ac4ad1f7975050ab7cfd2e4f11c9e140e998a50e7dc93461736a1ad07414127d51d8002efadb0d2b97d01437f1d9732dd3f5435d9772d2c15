/**
 * @description 自定义 element
 * @author wangfupeng
 */
import { Text } from 'slate';
export type TableCellProperty = {
    /** 用于设置单元格属性 */
    backgroundColor?: string;
    borderWidth?: string;
    borderStyle?: string;
    borderColor?: string;
    textAlign?: string;
};
export type TableCellElement = {
    type: 'table-cell';
    isHeader?: boolean;
    colSpan?: number;
    rowSpan?: number;
    width?: string;
    children: Text[];
    /** 用于设置单元格的 display 属性 */
    hidden?: boolean;
} & TableCellProperty;
export type TableRowElement = {
    type: 'table-row';
    children: TableCellElement[];
    height?: number;
};
export type TableElement = {
    type: 'table';
    width: string;
    children: TableRowElement[];
    /** resize bar */
    scrollWidth?: number;
    height?: number;
    resizingIndex?: number;
    isResizing?: boolean | null;
    isHoverCellBorder?: boolean;
    columnWidths?: number[];
    /** row resize */
    resizingRowIndex?: number;
    isResizingRow?: boolean | null;
    isHoverRowBorder?: boolean;
};
