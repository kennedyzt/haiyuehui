INSERT INTO `haiyuehui`.`business_partner` (`id`, `partner_code`, `partner_type`, `partner_name`, `foreign_name`, `partner_group`, `phone`, `mobilephone`, `fax`, `email`, `website`, `description`, `is_enable`, `is_delete`, `address`, `address_en`, `business_type`, `country_region_id`, `postal_code`, `contacts_last_name`, `contacts_first_name`, `contacts_gender`, `contacts_phone`, `contacts_mobilephone`, `contacts_email`, `contacts_fax`, `contacts_description`, `transaction_id`, `create_date`, `create_by`, `update_date`, `update_by`) VALUES ('0', '000', '1', '电商零售客户', '', '0', '', '', '', '', '', 'description', '', '\0', '', NULL, NULL, '2', NULL, '', '', '0', '', '', NULL, NULL, '', NULL, NOW(), NULL, NOW(), NULL);
INSERT INTO `haiyuehui`.`storage` (`id`, `storage_no`, `storage_name`, `description`, `is_delete`, `is_enable_location`, `create_date`, `create_by`, `update_date`, `update_by`) VALUES ('0', '000000', '虚拟仓', '不可删除', '\0', '\0', NOW(), NULL, NOW(), NULL);
INSERT INTO `haiyuehui`.`shop` (`id`, `shop_no`, `shop_name`, `is_delete`, `create_date`, `update_date`, `contact`, `telephone`, `address`) VALUES ('0', '0', '自营', '\0', NULL, NULL, NULL, NULL, NULL);

ALTER TABLE bank_account ADD INDEX bank_account_partner_id_index (`partner_id`);

ALTER TABLE bank_account ADD INDEX bank_account_account_no_index (`account_no`);

ALTER TABLE bank_account ADD INDEX bank_account_create_by_index (`create_by`);

ALTER TABLE business_partner ADD INDEX business_partner_create_by_index (`create_by`);

ALTER TABLE company ADD INDEX company_company_no_index (`company_no`);

ALTER TABLE country_region ADD INDEX country_region_create_by_index (`create_by`);

ALTER TABLE franchisee ADD INDEX franchisee_franchisee_no_index (`franchisee_no`);

ALTER TABLE groups ADD INDEX groups_user_account_index (`user_account`);
ALTER TABLE groups ADD INDEX groups_create_by_index (`create_by`);
ALTER TABLE groups ADD INDEX groups_is_delete_index (`is_delete`);
ALTER TABLE inventory ADD INDEX inventory_storage_location_id_index (`storage_location_id`);
ALTER TABLE inventory ADD INDEX inventory_create_by_index (`create_by`);
ALTER TABLE inventory_allocate ADD INDEX inventory_allocate_material_id_index (`material_id`);
ALTER TABLE inventory_allocate ADD INDEX inventory_allocate_material_no_index (`material_no`);
ALTER TABLE inventory_allocate ADD INDEX inventory_allocate_batch_number_index (`batch_number`);
ALTER TABLE inventory_allocate ADD INDEX inventory_allocate_from_location_id_index (`from_location_id`);
ALTER TABLE inventory_allocate ADD INDEX inventory_allocate_from_location_no_index (`from_location_no`);
ALTER TABLE inventory_allocate ADD INDEX inventory_allocate_from_storage_id_index (`from_storage_id`);
ALTER TABLE inventory_allocate ADD INDEX inventory_allocate_to_location_id_index (`to_location_id`);
ALTER TABLE inventory_allocate ADD INDEX inventory_allocate_to_location_no_index (`to_location_no`);
ALTER TABLE inventory_allocate ADD INDEX inventory_allocate_to_storage_id_index (`to_storage_id`);
ALTER TABLE inventory_allocate ADD INDEX inventory_allocate_create_by_index (`create_by`);

ALTER TABLE inventory_check ADD INDEX inventory_check_storage_id_index (`storage_id`);
ALTER TABLE inventory_check ADD INDEX inventory_check_storage_area_id_index (`storage_area_id`);
ALTER TABLE inventory_check ADD INDEX inventory_check_operator_id_index (`operator_id`);
ALTER TABLE inventory_check ADD INDEX inventory_check_status_index (`status`);
ALTER TABLE inventory_check ADD INDEX inventory_check_create_by_index (`create_by`);

ALTER TABLE inventory_check_item ADD INDEX inventory_check_item_batch_number_index (`batch_number`);
ALTER TABLE inventory_check_item ADD INDEX inventory_check_item_material_no_index (`material_no`);
ALTER TABLE inventory_check_item ADD INDEX inventory_check_item_location_no_index (`location_no`);

ALTER TABLE inventory_log ADD INDEX inventory_log_from_bills_no_index (`from_bills_no`);
ALTER TABLE inventory_log ADD INDEX inventory_log_batch_uuid_index (`batch_uuid`);
ALTER TABLE inventory_log ADD INDEX inventory_log_create_date_index (`create_date`);
ALTER TABLE inventory_log ADD INDEX inventory_log_create_by_index (`create_by`);

ALTER TABLE inventory_receipt ADD INDEX inventory_receipt_from_bills_no_index (`from_bills_no`);
ALTER TABLE inventory_receipt ADD INDEX inventory_receipt_inbound_storage_idindex (`inbound_storage_id`);
ALTER TABLE inventory_receipt ADD INDEX inventory_receipt_status_index (`status`);

ALTER TABLE inventory_receipt_item ADD INDEX inventory_receipt_item_batch_number_index (`batch_number`);
ALTER TABLE inventory_receipt_item ADD INDEX inventory_receipt_item_from_bills_no_index (`from_bills_no`);
ALTER TABLE inventory_receipt_item ADD INDEX inventory_receipt_item_status_index (`status`);

ALTER TABLE inventory_shipments_item ADD INDEX inventory_shipments_item_batch_number_index (`batch_number`);

ALTER TABLE inventory_warning ADD INDEX inventory_warning_material_id_index (`material_id`);
ALTER TABLE inventory_warning ADD INDEX inventory_warning_storage_id_index (`storage_id`);
ALTER TABLE material ADD INDEX material_partner_id_index (`partner_id`);

ALTER TABLE material_batch ADD INDEX material_batch_batch_number_index (`batch_number`);
ALTER TABLE material_batch ADD INDEX material_batch_create_date_index (`create_date`);
ALTER TABLE material_batch ADD INDEX material_batch_create_by_index (`create_by`);

ALTER TABLE material_type ADD INDEX material_type_parent_type_id_index (`parent_type_id`);
ALTER TABLE material_type ADD INDEX material_type_is_delete_index (`is_delete`);

ALTER TABLE material_unit ADD INDEX material_unit_unit_no_index (`unit_no`);
ALTER TABLE material_unit ADD INDEX material_unit_is_delete_index (`is_delete`);
ALTER TABLE menu_node ADD INDEX menu_node_user_account_index (`user_account`);
ALTER TABLE menu_node ADD INDEX menu_node_is_delete_index (`is_delete`);

ALTER TABLE operation_log ADD INDEX operation_log_create_by_index (`create_by`);

ALTER TABLE order_pick ADD INDEX order_pick_from_bills_no_index (`from_bills_no`);
ALTER TABLE order_pick ADD INDEX order_pick_storage_id_index (`storage_id`);

ALTER TABLE order_pick_item ADD INDEX order_pick_item_order_number_index (`order_number`);
ALTER TABLE order_pick_item ADD INDEX order_pick_item_material_id_index (`material_id`);
ALTER TABLE order_pick_item ADD INDEX order_pick_item_batch_number_index (`batch_number`);
ALTER TABLE order_pick_item ADD INDEX order_pick_item_storage_location_id_index (`storage_location_id`);
ALTER TABLE partner_group ADD INDEX partner_group_partner_type_index (`partner_type`);
ALTER TABLE partner_type ADD INDEX partner_type_is_delete_index (`is_delete`);
ALTER TABLE post_period ADD INDEX post_period_post_period_no_index (`post_period_no`);
ALTER TABLE purchase_apply_order ADD INDEX purchase_apply_order_applier_id_index (`applier_id`);

ALTER TABLE purchase_order ADD INDEX purchase_order_partner_id_index (`partner_id`);

ALTER TABLE purchase_order_item ADD INDEX purchase_order_item_material_id_index (`material_id`);
ALTER TABLE purchase_order_item ADD INDEX purchase_order_item_from_bills_no_index (`from_bills_no`);
ALTER TABLE purchase_order_item ADD INDEX purchase_order_item_ready_status_index (`ready_status`);

ALTER TABLE purchase_receipt ADD INDEX purchase_receipt_partner_id_index (`partner_id`);
ALTER TABLE purchase_receipt_item ADD INDEX purchase_receipt_item_batch_number_index (`batch_number`);
ALTER TABLE purchase_returns ADD INDEX purchase_returns_partner_id_index (`partner_id`);
ALTER TABLE purchase_returns_item ADD INDEX purchase_returns_item_batch_number_index (`batch_number`);

ALTER TABLE ready_shipments ADD INDEX ready_shipments_from_bills_no_index (`from_bills_no`);
ALTER TABLE ready_shipments ADD INDEX ready_shipments_storage_id_index (`storage_id`);
ALTER TABLE ready_shipments ADD INDEX ready_shipments_partner_id_index (`partner_id`);

ALTER TABLE ready_shipments_item ADD INDEX ready_shipments_item_batch_number_index (`batch_number`);
ALTER TABLE sell_order ADD INDEX sell_order_from_bills_no_index (`from_bills_no`);
ALTER TABLE sell_order ADD INDEX sell_order_partner_id_index (`partner_id`);
ALTER TABLE sell_order_item ADD INDEX sell_order_item_batch_number_index (`batch_number`);
ALTER TABLE sell_returns ADD INDEX sell_returns_partner_id_index (`partner_id`);
ALTER TABLE sell_returns_item ADD INDEX sell_returns_item_batch_number_index (`batch_number`);

ALTER TABLE sell_shipments ADD INDEX sell_shipments_partner_id_index (`partner_id`);
ALTER TABLE sell_shipments ADD INDEX sell_shipments_franchisee_id_index (`franchisee_id`);
ALTER TABLE sell_shipments_item ADD INDEX sell_shipments_item_batch_number_index (`batch_number`);
ALTER TABLE shop ADD INDEX shop_shop_no_index (`shop_no`);
ALTER TABLE shop ADD INDEX shop_is_delete_index (`is_delete`);

ALTER TABLE `storage` ADD INDEX storage_is_enable_location_index (`is_enable_location`);
ALTER TABLE storage_area ADD INDEX storage_area_storage_id_index (`storage_id`);
ALTER TABLE storage_area ADD INDEX storage_area_area_no_index (`area_no`);
ALTER TABLE storage_area ADD INDEX storage_area_is_delete_index (`is_delete`);

ALTER TABLE storage_location ADD INDEX storage_location_location_no_index (`location_no`);
ALTER TABLE storage_location ADD INDEX storage_location_storage_id_index (`storage_id`);
ALTER TABLE storage_location ADD INDEX storage_location_storage_area_id_index (`storage_area_id`);

ALTER TABLE usr ADD INDEX usr_is_delete_index (`is_delete`);
ALTER TABLE usr ADD INDEX usr_user_type_index (`user_type`);
ALTER TABLE usr ADD INDEX usr_user_account_index (`user_account`);








































































