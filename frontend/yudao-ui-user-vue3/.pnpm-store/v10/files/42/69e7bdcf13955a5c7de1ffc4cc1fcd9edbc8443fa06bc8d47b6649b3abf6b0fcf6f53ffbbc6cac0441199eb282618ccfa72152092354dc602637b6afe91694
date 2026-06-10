/**
 * @description parse html
 * @author wangfupeng
 */
import { Descendant, Element as SlateElement } from 'slate';
import { IDomEditor } from '../editor/interface';
import { DOMElement } from '../utils/dom';
export declare const TEXT_TAGS: string[];
export type PreParseHtmlFnType = ($node: DOMElement) => DOMElement;
export interface IPreParseHtmlConf {
    selector: string;
    preParseHtml: PreParseHtmlFnType;
}
export declare const PRE_PARSE_HTML_CONF_LIST: IPreParseHtmlConf[];
/**
 * 注册 pre-parse html 配置
 * @param conf pre-parse html conf
 */
export declare function registerPreParseHtmlConf(conf: IPreParseHtmlConf): void;
export type ParseStyleHtmlFnType = ($node: DOMElement, node: Descendant, editor: IDomEditor) => Descendant;
export declare const PARSE_STYLE_HTML_FN_LIST: ParseStyleHtmlFnType[];
/**
 * 注册 parseStyleHtml 函数
 * @param fn parse style html 的函数
 */
export declare function registerParseStyleHtmlHandler(fn: ParseStyleHtmlFnType): void;
export type ParseElemHtmlFnType = ($elem: DOMElement, children: Descendant[], editor: IDomEditor) => SlateElement | SlateElement[];
export declare const PARSE_ELEM_HTML_CONF: {
    [key: string]: ParseElemHtmlFnType;
};
export interface IParseElemHtmlConf {
    selector: string;
    parseElemHtml: ParseElemHtmlFnType;
}
export declare function registerParseElemHtmlConf(conf: IParseElemHtmlConf): void;
