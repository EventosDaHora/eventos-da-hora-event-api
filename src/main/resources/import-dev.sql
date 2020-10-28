INSERT INTO category(id, ds_categoria, nm_category)
VALUES (1, 'Categoria de Musica', 'Musica');

INSERT INTO category(id, ds_categoria, nm_category)
VALUES (2, 'Categoria de Arte', 'Arte');

INSERT INTO category(id, ds_categoria, nm_category)
VALUES (3, 'Categoria de Teatro', 'Teatro');

INSERT INTO category(id, ds_categoria, nm_category)
VALUES (4, 'Categoria de Shows', 'Show');

INSERT INTO category(id, ds_categoria, nm_category)
VALUES (5, 'Categoria de Esportes', 'Esporte');

INSERT INTO category(id, ds_categoria, nm_category)
VALUES (6, 'Categoria de Cinemas', 'Cinema');



INSERT INTO public.country(id, ds_country) VALUES (1, 'BRASIL');
INSERT INTO public.city(id, ds_city) VALUES (1, 'BRASILIA');

INSERT INTO public.statusevent(id, ds_status_event) VALUES (1, 'AGENDADO');


INSERT INTO public.event(id, dt_event, ds_event, ds_address, cep, ds_complemento, nm_localization, nu_address, nm_event,
                         id_category, id_city, id_country, id_status_event)
VALUES (1, current_date + 10 + 1, 'Evento muito legal', 'Q. 102, CJ. 24', '71692170', 'Complemento', 'Estadio Mane Garrincha',
        12, 'Escola de Rock', 1, 1, 1, 1);

INSERT INTO public.imageevent(id, id_image, ds_image_type, id_event) VALUES (1, '123', 'THUMBNAIL', 1);
INSERT INTO public.imageevent(id, id_image, ds_image_type, id_event) VALUES (2, '1234', 'BANNER', 1);


INSERT INTO public.section(id, vl_amount, ds_section, metadata, nm_section, id_event)
VALUES (1, 100.20, 'Arquibancada muito top', null, 'ARQUIBANCADA', 1);
INSERT INTO public.section(id, vl_amount, ds_section, metadata, nm_section, id_event)
VALUES (2, 500.20, 'Camarote muito top', null, 'CAMAROTE', 1);


INSERT INTO public.ticket(id, qt_ticket_initial, id_section) VALUES (1, 100, 1);
INSERT INTO public.ticket(id, qt_ticket_initial, id_section) VALUES (2, 50, 2);
