INSERT INTO tb_category(id_category, ds_categoria, nm_category)
VALUES (1, 'Categoria de Musica', 'Musica');

INSERT INTO tb_category(id_category, ds_categoria, nm_category)
VALUES (2, 'Categoria de Arte', 'Arte');

INSERT INTO tb_category(id_category, ds_categoria, nm_category)
VALUES (3, 'Categoria de Teatro', 'Teatro');

INSERT INTO tb_category(id_category, ds_categoria, nm_category)
VALUES (4, 'Categoria de Shows', 'Show');

INSERT INTO tb_category(id_category, ds_categoria, nm_category)
VALUES (5, 'Categoria de Esportes', 'Esporte');

INSERT INTO tb_category(id_category, ds_categoria, nm_category)
VALUES (6, 'Categoria de Cinemas', 'Cinema');

SELECT setval('seq_category', 6);

INSERT INTO public.country(id, ds_country) VALUES (1, 'BRASIL');
INSERT INTO public.city(id, ds_city) VALUES (1, 'Brasília');
INSERT INTO public.city(id, ds_city) VALUES (2, 'São Paulo');
INSERT INTO public.city(id, ds_city) VALUES (3, 'Belo Horizonte');

INSERT INTO public.statusevent(id, ds_status_event) VALUES (1, 'AGENDADO');
INSERT INTO public.statusevent(id, ds_status_event) VALUES (2, 'CANCELADO');


-- INSERT de EVENTO KISS--
INSERT INTO public.event(id_event, dt_event, ds_event, ds_address, cep, ds_complemento, nm_localization, nu_address, nm_event,
                         id_category, id_city, id_country, id_status_event)
VALUES (1, current_date + 11, 'Evento muito legal', 'Q. 102, CJ. 24', '71692170', 'Complemento', 'Estadio Mane Garrincha',
        12, 'KISS - END OF THE ROAD', 4, 1, 1, 1);

INSERT INTO public.imageevent(id, id_image, ds_image_type, id_event) VALUES (1, '6f91c0d1-1265-4aa6-8ffd-41d064406a13', 'THUMBNAIL', 1);
INSERT INTO public.imageevent(id, id_image, ds_image_type, id_event) VALUES (2, '32544f47-d4c0-4352-9d59-4707f91eff8b', 'BANNER', 1);


INSERT INTO public.section(id, vl_amount, ds_section, metadata, nm_section, id_event)
VALUES (1, 130, 'Arquibancada muito top', null, 'ARQUIBANCADA', 1);
INSERT INTO public.section(id, vl_amount, ds_section, metadata, nm_section, id_event)
VALUES (2, 500, 'Camarote muito top', null, 'CAMAROTE', 1);


INSERT INTO public.ticket(id, qt_ticket_initial, id_section) VALUES (1, 100, 1);
INSERT INTO public.ticket(id, qt_ticket_initial, id_section) VALUES (2, 50, 2);


-- INSERT de EVENTO AC/DC--
INSERT INTO public.event(id_event, dt_event, ds_event, ds_address, cep, ds_complemento, nm_localization, nu_address, nm_event,
                         id_category, id_city, id_country, id_status_event)
VALUES (2, current_date + 3 + 1, 'Evento muito legal', 'Q. 102, CJ. 24', '71692170', 'Complemento', 'Pacaembu',
        12, 'AC/DC - LOST IN HELL', 4, 2, 1, 1);

INSERT INTO public.imageevent(id, id_image, ds_image_type, id_event) VALUES (3, '5c3b6ebd-6682-46d0-99e4-e0fa53d506d1', 'THUMBNAIL', 2);
INSERT INTO public.imageevent(id, id_image, ds_image_type, id_event) VALUES (4, 'f8629f7d-bdec-4e37-b540-d65adb6adfbf', 'BANNER', 2);


INSERT INTO public.section(id, vl_amount, ds_section, metadata, nm_section, id_event)
VALUES (3, 80, 'Arquibancada muito top', null, 'ARQUIBANCADA', 2);
INSERT INTO public.section(id, vl_amount, ds_section, metadata, nm_section, id_event)
VALUES (4, 250, 'Camarote muito top', null, 'CAMAROTE', 2);
INSERT INTO public.section(id, vl_amount, ds_section, metadata, nm_section, id_event)
VALUES (5, 30, 'Pista muito top', null, 'PISTA', 2);

INSERT INTO public.ticket(id, qt_ticket_initial, id_section) VALUES (3, 100, 3);
INSERT INTO public.ticket(id, qt_ticket_initial, id_section) VALUES (4, 50, 4);
INSERT INTO public.ticket(id, qt_ticket_initial, id_section) VALUES (5, 100, 5);


-- INSERT de EVENTO --
INSERT INTO public.event(id_event, dt_event, ds_event, ds_address, cep, ds_complemento, nm_localization, nu_address, nm_event,
                         id_category, id_city, id_country, id_status_event)
VALUES (3, current_date + 22, 'Evento muito legal', 'Q. 102, CJ. 24', '71692170', 'Complemento', 'Mineirao',
        12, 'Tributo ao Mamonas Assasinas ', 4, 3, 1, 1);

INSERT INTO public.imageevent(id, id_image, ds_image_type, id_event) VALUES (5, '7ca11856-ff15-4cd6-b67e-7994fe7ba9e8', 'THUMBNAIL', 3);
INSERT INTO public.imageevent(id, id_image, ds_image_type, id_event) VALUES (6, 'd9a048bd-12f1-43cd-b601-d2e6ba784a84', 'BANNER', 3);


INSERT INTO public.section(id, vl_amount, ds_section, metadata, nm_section, id_event)
VALUES (6, 70, 'Arquibancada muito top', null, 'ARQUIBANCADA', 3);
INSERT INTO public.section(id, vl_amount, ds_section, metadata, nm_section, id_event)
VALUES (7, 250, 'Camarote muito top', null, 'CAMAROTE', 3);
INSERT INTO public.section(id, vl_amount, ds_section, metadata, nm_section, id_event)
VALUES (8, 30, 'Pista muito top', null, 'PISTA', 3);

INSERT INTO public.ticket(id, qt_ticket_initial, id_section) VALUES (6, 100, 6);
INSERT INTO public.ticket(id, qt_ticket_initial, id_section) VALUES (7, 50, 7);
INSERT INTO public.ticket(id, qt_ticket_initial, id_section) VALUES (8, 100, 8);
