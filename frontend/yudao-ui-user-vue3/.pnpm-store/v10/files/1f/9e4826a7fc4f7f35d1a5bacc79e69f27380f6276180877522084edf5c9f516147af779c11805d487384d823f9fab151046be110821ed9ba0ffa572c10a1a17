/**
 * @description link menu entry
 * @author wangfupeng
 */
import EditLink from './EditLink';
import InsertLink from './InsertLink';
import UnLink from './UnLink';
import ViewLink from './ViewLink';
declare const insertLinkMenuConf: {
    key: string;
    factory(): InsertLink;
    config: {
        checkLink(_text: string, _url: string): boolean | string | undefined;
        parseLinkUrl(url: string): string;
    };
};
declare const editLinkMenuConf: {
    key: string;
    factory(): EditLink;
    config: {
        checkLink(_text: string, _url: string): boolean | string | undefined;
        parseLinkUrl(url: string): string;
    };
};
declare const unLinkMenuConf: {
    key: string;
    factory(): UnLink;
};
declare const viewLinkMenuConf: {
    key: string;
    factory(): ViewLink;
};
export { editLinkMenuConf, insertLinkMenuConf, unLinkMenuConf, viewLinkMenuConf, };
