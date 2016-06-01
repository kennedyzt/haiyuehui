/*==============================================================*/
/* Table: company                                               */
/*==============================================================*/
create table company 
(
   id                   int                           primary key auto_increment not null,
   company_name         varchar(20)                    not null,
   type                 int                            not null,
   parent_id            int                            null,
   is_head_company      bit                            not null,
   descrition           varchar(200)                   null
);
/*==============================================================*/
/* Table: company_user                                          */
/*==============================================================*/
create table company_user 
(
   id                   int                            primary key auto_increment not null,
   company_id           int                            not null,
   employee_id          int                            null,
   is_delete            bit                            not null,
   create_date          datetime                       null,
   create_by            int                            null
);
create table employee 
(
   id                   int                            primary key auto_increment not null,
   emp_no               varchar(20)                    not null,
   emp_name             varchar(20)                    not null,
   gender               int                            null,
   age                  int(3)                         null,
   address              varchar(100)                   null,
   email                varchar(50)                    null,
   zipcode              int(6)                         null,
   mobilephone          int(11)                        null,
   telephone            int(15)                        null,
   qq                   int(15)                        null,
   company_id           int                            not null,
   create_date          datetime                       null,
   create_by            int                            null,
   update_date          datetime                       null,
   update_by            int                            null
);
/*==============================================================*/
/* Table: "user"                                                */
/*==============================================================*/
create table usr
(
   id                   int                            primary key auto_increment not null,
   user_name            varchar(20)                    not null,
   nickname             varchar(20)                    null,
   has_permission       bit                            not null,
   is_delete            bit                            not null,
   group_id             int(11)                        null,
   employee_id          int                            null,
   pwd_hash             varchar(128)                   not null,
   pwd_iterator         int                            not null,
   pwd_salt             varchar(10)                    not null,
   last_login_date      datetime                       null,
   create_date          datetime                       null,
   create_by            int                            null,
   update_date          datetime                       null,
   update_by            int                            null
);
/*==============================================================*/
/* Table: menu_tree                                             */
/*==============================================================*/
create table menu_tree 
(
   id                   int                            primary key auto_increment not null,
   tree_name            varchar(20)                    not null,
   description          varchar(100)                   null,
   is_delete            bit                            not null,
   create_date          datetime                       null,
   create_by            int                            null,
   update_date          datetime                       null,
   update_by            int                            null
);
/*==============================================================*/
/* Table: menu_node                                             */
/*==============================================================*/
create table menu_node 
(
   id                   int                            primary key auto_increment not null,
   node_name            varchar(20)                    not null,
   sequence             int                            not null,
   description          varchar(100)                   null,
   parent_id            int                            null,
   menu_tree_id         int                            not null,
   level                int                            not null,
   is_root              bit                            not null,
   node_url             varchar(40)                    null,
   is_delete            bit                            not null,
   create_date          datetime                       null,
   create_by            int                            null,
   update_date          datetime                       null,
   update_by            int                            null
);
/*==============================================================*/
/* Table: permission                                            */
/*==============================================================*/
create table permission 
(
   id                   int                           primary key auto_increment not null,
   group_id             int                            not null,
   node_id              int                            not null,
   create_date          datetime                       null,
   create_by            int                            null
);
/*==============================================================*/
/* Table: user_permission                                       */
/*==============================================================*/
create table user_permission 
(
   id                   int                            primary key auto_increment not null,
   user_id              int                            not null,
   permission_id        int                            not null,
   create_date          datetime                       null,
   create_by            int                            null,
   update_date          datetime                       null,
   update_by            int                            null
);
/*==============================================================*/
/* Table: "group"                                               */
/*==============================================================*/
create table groups
(
   id                   int                            primary key auto_increment not null,
   group_name           varchar(20)                    not null,
   description          varchar(100)                   null,
   is_delete            bit                            not null,
   create_date          datetime                       null,
   create_by            int                            null,
   update_date          datetime                       null,
   update_by            int                            null
);
/*==============================================================*/
/* Table: user_group                                            */
/*==============================================================*/
create table user_group 
(
   id                   int                            primary key auto_increment not null,
   user_id              int                            not null,
   group_id             int                            not null,
   is_delete            bit                            not null,
   create_date          datetime                       null,
   create_by            int                            null
);
create index company_parent_id_index on haiyuehui.company(parent_id);
create index company_is_head_company_index on haiyuehui.company(is_head_company);
create index company_user_company_id_index on haiyuehui.company_user(company_id);
create index company_user_employee_id_index on haiyuehui.company_user(employee_id);
create index employee_emp_no_index on haiyuehui.employee(emp_no);
create index employee_mobilephone_index on haiyuehui.employee(mobilephone);
create index employee_company_id_index on haiyuehui.employee(company_id);
create index menu_node_sequence_index on haiyuehui.menu_node(sequence);
create index menu_node_parent_id_index on haiyuehui.menu_node(parent_id);
create index menu_node_menu_tree_id_index on haiyuehui.menu_node(menu_tree_id);
create index menu_node_level_index on haiyuehui.menu_node(level);
create index menu_node_sequence_index on haiyuehui.menu_node(sequence);
create index permission_group_id_index on haiyuehui.permission(group_id);
create index permission_node_id_index on haiyuehui.permission(node_id);
create index user_group_user_id_index on haiyuehui.user_group(user_id);
create index user_group_group_id_index on haiyuehui.user_group(group_id);
create index user_permission_user_id_index on haiyuehui.user_permission(user_id);
create index user_permission_permission_id_index on haiyuehui.user_permission(permission_id);
create index usr_group_id_index on haiyuehui.usr(group_id);
create index usr_employee_id_index on haiyuehui.usr(employee_id);

ALTER TABLE haiyuehui.company_user ADD CONSTRAINT company_user_company_id_fk FOREIGN KEY (company_id) REFERENCES haiyuehui.company(id);
ALTER TABLE haiyuehui.company_user ADD CONSTRAINT company_user_employee_id_fk FOREIGN KEY (employee_id) REFERENCES haiyuehui.employee(id);
ALTER TABLE haiyuehui.employee ADD CONSTRAINT employee_company_id_fk FOREIGN KEY (company_id) REFERENCES haiyuehui.company(id);
ALTER TABLE haiyuehui.usr ADD CONSTRAINT usr_group_id_fk FOREIGN KEY (group_id) REFERENCES haiyuehui.groups(id);
ALTER TABLE haiyuehui.usr ADD CONSTRAINT usr_employee_id_fk FOREIGN KEY (employee_id) REFERENCES haiyuehui.employee(id);
ALTER TABLE haiyuehui.user_group ADD CONSTRAINT user_group_user_id_fk FOREIGN KEY (user_id) REFERENCES haiyuehui.usr(id);
ALTER TABLE haiyuehui.user_group ADD CONSTRAINT user_group_group_id_fk FOREIGN KEY (group_id) REFERENCES haiyuehui.groups(id);
ALTER TABLE haiyuehui.permission ADD CONSTRAINT permission_group_id_fk FOREIGN KEY (group_id) REFERENCES haiyuehui.groups(id);
ALTER TABLE haiyuehui.permission ADD CONSTRAINT permission_node_id_fk FOREIGN KEY (node_id) REFERENCES haiyuehui.menu_node(id);
ALTER TABLE haiyuehui.user_permission ADD CONSTRAINT user_permission_user_id_fk FOREIGN KEY (user_id) REFERENCES haiyuehui.usr(id);
ALTER TABLE haiyuehui.user_permission ADD CONSTRAINT user_permission_permission_id_fk FOREIGN KEY (permission_id) REFERENCES haiyuehui.permission(id);
ALTER TABLE haiyuehui.menu_node ADD CONSTRAINT menu_node_menu_tree_id_fk FOREIGN KEY (menu_tree_id) REFERENCES haiyuehui.menu_tree(id);
